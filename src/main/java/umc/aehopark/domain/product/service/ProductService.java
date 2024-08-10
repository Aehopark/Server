package umc.aehopark.domain.product.service;

import org.springframework.data.domain.Page;

import umc.aehopark.domain.product.dto.ProductRequest;
import umc.aehopark.domain.product.dto.ProductResponse;
import umc.aehopark.domain.product.entity.Ingredient;

public interface ProductService {
	// 판매 상품 등록
	public ProductResponse registerProduct(ProductRequest request);

	// 판매 상품 변경
	public ProductResponse alterProduct(Long productId, ProductRequest request);

	// 판매 상품 삭제
	public ProductResponse deleteProduct(Long productId);

	// 모든 카테고리 모든 식재료 조회 + 페이징
	public Page<Ingredient> searchAllProducts(Integer page);

	// 모든 카테고리 특정 식재료 조회 + 페이징
	public Page<Ingredient> searchProduct(String ingredientName, Integer page);

	// 카테고리별 모든 식재료 조회 + 페이징
	public Page<Ingredient> searchAllProductByCategory(String categoryName, Integer page);

	// 카테고리별 특정 식재료 조회 + 페이징
	public Page<Ingredient> searchProductByCategory(String categoryName, String ingredientName, Integer page);
}
