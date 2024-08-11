package umc.aehopark.domain.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.service.BasketCheckService;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/product")
public class BasketController {

	@Autowired
	private BasketCheckService basketService;

	@GetMapping("/good-ingredient/{userId}")
	public ResponseEntity<ApiResponse<List<BasketResponseDto.ProductDto>>> searchBasket(@PathVariable Long userId) {
		List<BasketResponseDto.ProductDto> response = basketService.searchBasket(userId);
		return ResponseEntity.ok().body(new ApiResponse<>("장바구니 조회 성공", response));
	}
}