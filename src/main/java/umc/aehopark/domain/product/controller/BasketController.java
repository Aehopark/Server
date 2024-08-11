package umc.aehopark.domain.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.service.BasketService;
import umc.aehopark.domain.product.dto.request.BasketAddRequest;
import umc.aehopark.domain.product.dto.request.BasketDeleteRequest;
import umc.aehopark.domain.product.service.BasketServiceImpl;
import umc.aehopark.global.common.ApiResponse;
import umc.aehopark.global.exception.ConflictException;

@RestController
@RequestMapping("/basket")
public class BasketController {

	@Autowired
	private BasketServiceImpl basketService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<BasketResponseDto.ProductDto>>> searchBasket(@RequestParam Long userId) {
		try {
			List<BasketResponseDto.ProductDto> response = basketService.searchBasket(userId);
			return ResponseEntity.ok().body(new ApiResponse<>("장바구니 조회 성공", response));
		} catch (IllegalArgumentException e) { // 400 Bad Request - 클라이언트 요청 오류
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었습니다.", null));
		} catch (ConflictException e) { // 404 Conflict - 상품이 조회되지 않을 경우
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("장바구니가 비어있습니다.", null));
		}
	}

	// POST /add
	// 장바구니 추가 API
	// BearToken
	// @RequestBody BasketAddRequest
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Void>> addBasket(@RequestBody BasketAddRequest basketAddRequest, @RequestParam Long userId) {
		try {
			basketService.addBasket(basketAddRequest, userId);
			return ResponseEntity.ok().body(new ApiResponse<>("장바구니 추가 성공", null));
		} catch (IllegalArgumentException e) { //   400 Bad Request - 클라이언트 요청 오류
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었습니다.", null));
		} catch (ConflictException e) { //   404 Conflict - 상품이 조회되지 않을 경우
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("존재하지 않는 상품입니다.", null));
		}
	}

	// POST /delete
	// 장바구니 삭제 API
	// BearToken
	// @RequestBody BasketDeleteRequest
	@PostMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> deleteBasket(@RequestBody BasketDeleteRequest basketDeleteRequest, @RequestParam Long userId) {
		try {
			basketService.deleteBasket(basketDeleteRequest, userId);
			return ResponseEntity.ok().body(new ApiResponse<>("장바구니 삭제 성공", null));
		} catch (IllegalArgumentException e) { // 400 Bad Request - 클라이언트 요청 오류
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("요청이 잘못되었습니다.", null));
		} catch (ConflictException e) { // 404 Conflict - 상품이 조회되지 않을 경우
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("존재하지 않는 상품입니다.", null));
		}
	}


}