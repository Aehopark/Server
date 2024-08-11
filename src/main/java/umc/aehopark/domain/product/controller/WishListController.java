package umc.aehopark.domain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.WishListCheckResponseDto;
import umc.aehopark.domain.product.service.WishListService;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	private WishListCheckResponseDto wishListCheckResponseDto;

	@GetMapping("/{user_id}")
	public ApiResponse<Page<WishListCheckResponseDto>> searchWishList(
		@PathVariable("user_id") Long userId,
		@RequestParam(defaultValue = "1") int page) {
		Pageable pageable = PageRequest.of(page - 1, 10); // 페이지는 1부터 시작하지만, PageRequest는 0부터 시작
		Page<WishListCheckResponseDto> wishLists = wishListService.searchWishList(userId, pageable);
		return ApiResponse.onSuccess(wishLists);
	}

	@PostMapping("/{user_id}")
	public ApiResponse<Void> isWishList(
		@PathVariable("user_id") long userId,
		@RequestParam("ingredient_id") long ingredientId) {
		wishListService.isWishList(userId, ingredientId);
		return ApiResponse.onSuccess(null);
	}
}
