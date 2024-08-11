package umc.aehopark.domain.product.service;

import java.util.List;

import umc.aehopark.domain.product.dto.RecentProductResponseDto;

public interface RecentlyViewedIngredientCheckService {
	List<RecentProductResponseDto> searchRecentProduct(Long userId, int page, int size);
}
