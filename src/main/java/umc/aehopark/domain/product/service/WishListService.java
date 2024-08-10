package umc.aehopark.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.aehopark.domain.product.dto.WishListCheckResponseDto;

public interface WishListService {
	Page<WishListCheckResponseDto> searchWishList(Long userId, Pageable pageable);

	void isWishList(long userId, long ingredientId);
}