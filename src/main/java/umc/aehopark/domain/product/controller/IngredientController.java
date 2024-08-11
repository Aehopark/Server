package umc.aehopark.domain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.ProductDetailSearchResponseDto;
import umc.aehopark.domain.product.service.IngredientServiceImpl;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

	private final IngredientServiceImpl ingredientService;

	@Autowired
	public IngredientController(IngredientServiceImpl ingredientService) {
		this.ingredientService = ingredientService;
	}

	@GetMapping("/{ingredientId}")
	public ApiResponse<ProductDetailSearchResponseDto> searchProductDetail(
		@PathVariable("ingredientId") Long ingredientId) {
		ProductDetailSearchResponseDto ingredients = ingredientService.searchProductDetail(ingredientId);
		return ApiResponse.onSuccess(ingredients);
	}
}