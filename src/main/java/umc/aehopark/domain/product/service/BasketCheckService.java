package umc.aehopark.domain.product.service;

import java.util.List;

import umc.aehopark.domain.product.dto.BasketResponseDto;

public interface BasketCheckService {
	List<BasketResponseDto.ProductDto> searchBasket(Long userId);
}


