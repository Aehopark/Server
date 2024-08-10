package umc.aehopark.domain.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.service.BasketCheckService;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/order")
public class BasketController {

	private final BasketCheckService basketCheckService;

	@Autowired
	public BasketController(BasketCheckService basketCheckService) {
		this.basketCheckService = basketCheckService;
	}

	// 특정 사용자의 장바구니 상품 조회
	@GetMapping("/{userId}/basket")
	public ApiResponse<List<BasketResponseDto>> searchBasket(@PathVariable("userId") int userId) {
		Long userIdInt = Long.valueOf(userId);
		List<BasketResponseDto> basketList = basketCheckService.searchBasket(userId);
		BasketResponseDto responseDto = new BasketResponseDto();
		return ApiResponse.onSuccess(basketList);
	}
}