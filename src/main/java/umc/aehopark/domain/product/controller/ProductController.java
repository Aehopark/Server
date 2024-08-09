package umc.aehopark.domain.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.IngredientSearchResponse;
import umc.aehopark.domain.product.dto.ProductRequest;
import umc.aehopark.domain.product.dto.ProductResponse;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.service.ProductServiceImpl;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private final ProductServiceImpl productService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}

	//판매 상품 등록
	@ResponseBody
	@PostMapping("/register")
	public ApiResponse<?> registerProduct(
		@Validated @RequestBody ProductRequest request) {

		ProductResponse response = productService.registerProduct(request);

		return ApiResponse.onSuccess(response);
	}

	// 판매 상품 변경
	// "/alter?product_id={product_id}"
	@PatchMapping("/alter")
	public ApiResponse<?> alterProduct(
		@RequestParam long product_id,
		@Validated @RequestBody ProductRequest request) {

		ProductResponse response = productService.alterProduct(product_id, request);

		return ApiResponse.onSuccess(response);
	}

	// 판매 상품 삭제
	// "/delete?product_id={product_id}"
	@DeleteMapping("/delete")
	public ApiResponse<?> deleteProduct(
		@RequestParam Long product_id) {
		ProductResponse response = productService.deleteProduct(product_id);

		return ApiResponse.onSuccess(response);
	}

	// 모든 카테고리 모든 식재료 조회 + 페이징
	// "?page={page}"
	@GetMapping("")
	public ApiResponse<?> searchAllProduct(
		@RequestParam(defaultValue = "0") int page) {

		Page<Ingredient> allIngredientsPage = productService.searchAllProducts(page);

		List<IngredientSearchResponse> results = allIngredientsPage.getContent()
			.stream()
			.map(IngredientSearchResponse::new)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(results);
	}

	// 모든 카테고리 특정 식재료 조회 + 페이징
	// "/search?ingredient={ingredient_name}&page={page}"
	@GetMapping("/search")
	public ApiResponse<?> searchProduct(
		@RequestParam String ingredient_name,
		@RequestParam(defaultValue = "0") int page) {

		Page<Ingredient> ingredientsPage = productService.searchProduct(ingredient_name, page);

		List<IngredientSearchResponse> results = ingredientsPage.getContent()
			.stream()
			.map(IngredientSearchResponse::new)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(results);
	}

	// 카테고리별 모든 식재료 조회 + 페이징
	// "/{category_name}?page={page}"
	@GetMapping("/{category_name}")
	public ApiResponse<?> searchAllProductByCategory(
		@PathVariable("category_name") String category_name,
		@RequestParam(defaultValue = "0") int page) {

		Page<Ingredient> allIngredientsByCategoryPage = productService.searchAllProductByCategory(category_name,
			page);

		List<IngredientSearchResponse> results = allIngredientsByCategoryPage.getContent()
			.stream()
			.map(IngredientSearchResponse::new)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(results);
	}

	// 카테고리별 특정 식재료 조회 + 페이징
	// "/{category_name}/search?ingredient={ingredient_name}&?page={num}"
	@GetMapping("/{category_name}/search")
	public ApiResponse<?> searchProductByCategory(
		@PathVariable("category_name") String categoryName,
		@RequestParam String ingredient_name,
		@RequestParam(defaultValue = "0") int page) {

		Page<Ingredient> allIngredientsByCategoryPage = productService.searchProductByCategory(
			categoryName, ingredient_name, page);

		List<IngredientSearchResponse> results = allIngredientsByCategoryPage.getContent()
			.stream()
			.map(IngredientSearchResponse::new)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(results);
	}

}

