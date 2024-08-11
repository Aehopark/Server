package umc.aehopark.domain.product.service;

import java.util.List;

import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.dto.request.BasketAddRequest;
import umc.aehopark.domain.product.dto.request.BasketDeleteRequest;

public interface BasketService {
	List<BasketResponseDto.ProductDto> searchBasket(Long userId);

	void addBasket(BasketAddRequest request, Long userId);

	void deleteBasket(BasketDeleteRequest request, Long userId);
}


