package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.entity.Basket;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.repository.BasketRepository;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.domain.user.repository.UserRepository;


@Service
@Slf4j
public class BasketCheckServiceImpl implements BasketCheckService {

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BasketResponseDto.ProductDto> searchBasket(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));

		List<Basket> baskets = basketRepository.findByUser(user);

		List<BasketResponseDto.ProductDto> productDtos = baskets.stream()
			.map(basket -> {
				Product product = basket.getProduct();
				return new BasketResponseDto.ProductDto(
					product.getId(),
					product.getName(),
					product.getPrice(),
					product.getIngredient().getCategory().getName(),
					product.getIngredient().getIngredientImages().isEmpty() ? null : product.getIngredient().getIngredientImages().get(0).getImageUrl(),
					product.getIngredient().getWishLists().isEmpty() ? 0 : 1
				);
			})
			.collect(Collectors.toList());

		return productDtos;
	}
}