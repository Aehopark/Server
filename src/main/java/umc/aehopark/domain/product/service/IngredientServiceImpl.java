package umc.aehopark.domain.product.service;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import umc.aehopark.domain.product.dto.ProductDetailSearchResponseDto;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.PlatformPrice;
import umc.aehopark.domain.product.entity.Price;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.repository.IngredientRepository;
import umc.aehopark.domain.product.repository.PlatformPriceRepository;
import umc.aehopark.domain.product.repository.PriceRepository;
import umc.aehopark.domain.product.repository.ProductRepository;

// 특정 식재료 조회
@Getter
@Setter
@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private PriceRepository priceRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PlatformPriceRepository platformPriceRepository;

	@Override
	public ProductDetailSearchResponseDto searchProductDetail(Long ingredientId) {
		Ingredient ingredient = ingredientRepository.findById(ingredientId)
			.orElseThrow(() -> new RuntimeException("Ingredient not found"));
		return convertToDto(ingredient);
	}

	private ProductDetailSearchResponseDto convertToDto(Ingredient ingredient) {
		List<Price> prices = priceRepository.findByIngredient(ingredient);
		List<Product> products = productRepository.findByIngredient(ingredient);
		List<PlatformPrice> platformPrices = platformPriceRepository.findByIngredient(ingredient);

		BigInteger monthTopPrice = priceRepository.findMaxPriceByIngredientId(ingredient.getId());

		// 가장 최근 날짜의 가격을 찾습니다.
		BigInteger recentPrice = prices.stream()
			.max(Comparator.comparing(Price::getUpdatedAt))
			.map(Price::getPrice)
			.orElse(BigInteger.ZERO);

		// totals 리스트를 ingredient의 모든 PriceDetailResponseDto 값으로 설정합니다.
		List<ProductDetailSearchResponseDto.PriceDetailResponseDto> totals = prices.stream()
			.map(price -> new ProductDetailSearchResponseDto.PriceDetailResponseDto(price.getPrice(),
				price.getUpdatedAt()))
			.collect(Collectors.toList());

		ProductDetailSearchResponseDto.PriceResponseDto priceResponseDto = new ProductDetailSearchResponseDto.PriceResponseDto(
			recentPrice, monthTopPrice, totals);

		List<ProductDetailSearchResponseDto.ProductResponseDto> productResult = products.stream()
			.map(product -> new ProductDetailSearchResponseDto.ProductResponseDto(product.getId(),
				product.getStore().getName(), product.getName(), product.getPrice()))
			.collect(Collectors.toList());

		List<ProductDetailSearchResponseDto.PlatFormResponseDto> platformResult = platformPrices.stream()
			.map(platform -> new ProductDetailSearchResponseDto.PlatFormResponseDto(platform.getId(),
				platform.getPlatform().name(), platform.getPrice()))
			.collect(Collectors.toList());

		return new ProductDetailSearchResponseDto(List.of(priceResponseDto), productResult, platformResult);
	}
}
