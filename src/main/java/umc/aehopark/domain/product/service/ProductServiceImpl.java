package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.aehopark.domain.product.dto.ProductRequest;
import umc.aehopark.domain.product.dto.ProductResponse;
import umc.aehopark.domain.product.entity.Category;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.entity.ProductImage;
import umc.aehopark.domain.product.handler.ProductHandler;
import umc.aehopark.domain.product.repository.CategoryRepository;
import umc.aehopark.domain.product.repository.IngredientRepository;
import umc.aehopark.domain.product.repository.ProductImageRepository;
import umc.aehopark.domain.product.repository.ProductRepository;
import umc.aehopark.domain.user.repository.UserRepository;
import umc.aehopark.global.status.ErrorStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final IngredientRepository ingredientRepository;
	private final CategoryRepository categoryRepository;
	private final ProductImageRepository productImageRepository;

	// 판매 상품 등록
	@Override
	@Transactional
	public ProductResponse registerProduct(ProductRequest request) {

		// 유저 아이디 존재 여부
		// User user = userRepository.findById(request.userId)
		// 	.orElseThrow(() -> new ProductHandler(ErrorStatus.USER_NOT_FOUND));

		// 카테고리 존재 여부
		if (!categoryRepository.existsByName(request.getCategory())) {
			throw new ProductHandler(ErrorStatus.CATEGORY_NOT_FOUND);
		}

		// 식재료 존재 여부
		Ingredient ingredient = ingredientRepository.findByName(request.getIngredientName())
			.orElseThrow(() -> new ProductHandler(ErrorStatus.INGREDIENT_NOT_FOUND));

		Product newProduct = Product.builder()
			.name(request.getName())
			.content(request.getContents())
			.price(request.getPrice())
			.stock(0) // 기본 재고 설정
			.deliveryFee(0) // 기본 배송비 설정
			.ingredient(ingredient)
			.build();

		Product savedProduct = productRepository.save(newProduct);

		List<ProductImage> productImages = request.getImageUrl().stream()
			.map(imageUrl -> ProductImage.builder()
				.url(imageUrl)
				.product(savedProduct)
				.build())
			.collect(Collectors.toList());

		productImageRepository.saveAll(productImages);

		ProductResponse result = new ProductResponse();
		result.setProductId(savedProduct.getId());
		result.setProductName(savedProduct.getName());

		return result;
	}

	// 판매 상품 변경
	@Override
	@Transactional
	public ProductResponse alterProduct(Long productId, ProductRequest request) {

		// 카테고리 존재 여부 확인
		if (!categoryRepository.existsByName(request.getCategory())) {
			throw new ProductHandler(ErrorStatus.CATEGORY_NOT_FOUND);
		}

		// 기존 상품 존재 여부 확인
		Product existingProduct = productRepository.findById(productId)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.PRODUCT_NOT_FOUND));

		// 식재료 존재 여부 확인
		Ingredient ingredient = ingredientRepository.findByName(request.getIngredientName())
			.orElseThrow(() -> new ProductHandler(ErrorStatus.INGREDIENT_NOT_FOUND));

		// 기존의 상품 이미지 삭제
		productImageRepository.deleteAll(productImageRepository.findByProduct(existingProduct));

		// 기존 상품 정보 업데이트
		existingProduct.setName(request.getName());
		existingProduct.setContent(request.getContents());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setIngredient(ingredient);

		// 새로운 이미지 추가
		List<ProductImage> productImages = request.getImageUrl().stream()
			.map(imageUrl -> ProductImage.builder()
				.url(imageUrl)
				.product(existingProduct)
				.build())
			.collect(Collectors.toList());

		productImageRepository.saveAll(productImages);

		ProductResponse result = new ProductResponse();
		result.setProductId(existingProduct.getId());
		result.setProductName(existingProduct.getName());

		return result;
	}

	// 판매 상품 삭제
	@Override
	@Transactional
	public ProductResponse deleteProduct(Long productId) {
		Product product = productRepository.findById(productId)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.PRODUCT_NOT_FOUND));

		ProductResponse result = new ProductResponse();
		result.setProductId(product.getId());
		result.setProductName(product.getName());

		productRepository.delete(product);

		return result;
	}

	// 모든 카테고리 모든 식재료 조회 + 페이징
	@Override
	public Page<Ingredient> searchAllProducts(Integer page) {

		return ingredientRepository.findAll(PageRequest.of(page, 10));
	}

	// 모든 카테고리 특정 식재료 조회 + 페이징
	@Override
	public Page<Ingredient> searchProduct(String ingredientName, Integer page) {

		ingredientRepository.findByName(ingredientName)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.INGREDIENT_NOT_FOUND));

		return ingredientRepository.findAllByName(PageRequest.of(page, 10), ingredientName);
	}

	// 카테고리별 모든 식재료 조회 + 페이징
	@Override
	public Page<Ingredient> searchAllProductByCategory(String categoryName, Integer page) {

		Category category = categoryRepository.findByName(categoryName)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.CATEGORY_NOT_FOUND));

		return ingredientRepository.findAllByCategory(PageRequest.of(page, 10), category);
	}

	// 카테고리별 특정 식재료 조회 + 페이징
	@Override
	public Page<Ingredient> searchProductByCategory(String categoryName, String ingredientName, Integer page) {

		Category category = categoryRepository.findByName(categoryName)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.CATEGORY_NOT_FOUND));

		ingredientRepository.findByName(ingredientName)
			.orElseThrow(() -> new ProductHandler(ErrorStatus.INGREDIENT_NOT_FOUND));

		return ingredientRepository.findAllByCategoryAndName(PageRequest.of(page, 10), category, ingredientName);
	}

}