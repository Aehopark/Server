package umc.aehopark.domain.product.service;

import umc.aehopark.domain.product.dto.ProductDetailSearchResponseDto;

public interface IngredientService {
	ProductDetailSearchResponseDto searchProductDetail(Long ingredientId);
}