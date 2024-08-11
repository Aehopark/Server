package umc.aehopark.domain.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umc.aehopark.domain.product.dto.GoodProductResponseDto;
import umc.aehopark.domain.product.service.GoodProductService;
import umc.aehopark.global.common.ApiResponse;

@RestController
@RequestMapping("/product")
public class GoodProductController {

	@Autowired
	private GoodProductService goodProductService;

	@GetMapping("/good-ingredient")
	public ApiResponse<GoodProductResponseDto> searchGoodProduct() {
		GoodProductResponseDto ingredient = goodProductService.searchGoodProduct();
		return ApiResponse.onSuccess(ingredient);
	}
}

