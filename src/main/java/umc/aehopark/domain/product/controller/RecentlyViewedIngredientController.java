package umc.aehopark.domain.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.RecentProductResponseDto;
import umc.aehopark.domain.product.service.RecentlyViewedIngredientCheckServiceImpl;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/product")
public class RecentlyViewedIngredientController {

	@Autowired
	private RecentlyViewedIngredientCheckServiceImpl recentlyViewedIngredientCheckService;

	@GetMapping("/{user_id}/recent")
	public ApiResponse<?> searchRecentProduct(
		@PathVariable("user_id") Long userId,
		@RequestParam(defaultValue = "0") int page) {
		List<RecentProductResponseDto> products = recentlyViewedIngredientCheckService.searchRecentProduct(userId, page,
			10);
		return ApiResponse.onSuccess(products);
	}
}