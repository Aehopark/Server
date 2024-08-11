package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import umc.aehopark.domain.product.dto.GoodProductResponseDto;
import umc.aehopark.domain.product.entity.Advertisement;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.RecommendList;
import umc.aehopark.domain.product.repository.AdvertisementRepository;
import umc.aehopark.domain.product.repository.IngredientImageRepository;
import umc.aehopark.domain.product.repository.RecommendListRepository;

@Getter
@Setter
@Service
public class GoodProductServiceImpl implements GoodProductService {

	@Autowired
	private AdvertisementRepository advertisementRepository;

	@Autowired
	private RecommendListRepository recommendListRepository;
	@Autowired
	private IngredientImageRepository ingredientImageRepository;

	@Override
	public GoodProductResponseDto searchGoodProduct() {
		List<Advertisement> advertisements = advertisementRepository.findTop10ByOrderByCreatedAtAsc();
		List<RecommendList> recommendLists = recommendListRepository.findRandomTop10();

		List<GoodProductResponseDto.AdDto> adDtos = advertisements.stream()
			.map(ad -> new GoodProductResponseDto.AdDto(ad.getId(), ad.getImageUrl()))
			.collect(Collectors.toList());

		List<GoodProductResponseDto.IngredientDto> ingredientDtos = recommendLists.stream()
			.map(recommend -> {
				Ingredient ingredient = recommend.getIngredient();
				return new GoodProductResponseDto.IngredientDto(
					ingredient.getId(),
					ingredient.getCategory().getName(),
					ingredient.getName(),
					ingredient.getIngredientImages() == null || ingredient.getIngredientImages().isEmpty() ? null :
						ingredient.getIngredientImages()
							.get(0)
							.getImageUrl(),
					ingredient.getWishLists() == null ? 0 :
						ingredient.getWishLists().size()
				);
			})
			.collect(Collectors.toList());

		return GoodProductResponseDto.builder()
			.ad(adDtos)
			.list(ingredientDtos)
			.build();
	}
}

