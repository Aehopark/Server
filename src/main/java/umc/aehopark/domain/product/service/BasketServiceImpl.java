package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.dto.request.BasketAddRequest;
import umc.aehopark.domain.product.dto.request.BasketDeleteRequest;
import umc.aehopark.domain.product.entity.Basket;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.repository.BasketRepository;
import umc.aehopark.domain.product.repository.ProductRepository;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.domain.user.repository.UserRepository;
import umc.aehopark.global.exception.NotFoundException;


@Service
@Slf4j
public class BasketServiceImpl implements BasketService {
	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;


	@Override
	public List<BasketResponseDto.ProductDto> searchBasket(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		List<Basket> baskets = basketRepository.findByUser(user);

		List<BasketResponseDto.ProductDto> productDtos = baskets.stream()
				.map(basket -> {
					Product product = basket.getProduct();
					return new BasketResponseDto.ProductDto(
							basket.getId(), // Add basketId
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

	@Override
	public void addBasket(BasketAddRequest request, Long userId) {
		// userId와 productId 유효성 검사
		if (userId == null) {
			throw new IllegalArgumentException("유저 Id가 비어있습니다..");
		}
		if (request.getProductId() == null) {
			throw new IllegalArgumentException("상품 Id가 비어있습니다.");
		}
		if (request.getQuantity() == null || request.getQuantity() <= 0) {
			throw new IllegalArgumentException("상품 수량이 유효하지 않습니다.");
		}

		// 사용자 존재 여부 확인
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

		// 상품 존재 여부 확인
		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new NotFoundException("Product not found with ID: " + request.getProductId()));

		// 장바구니에 상품 추가
		Basket basket = Basket.builder()
				.user(user)
				.product(product)
				.quantity(request.getQuantity())
				.build();
		basketRepository.save(basket);
	}

	@Override
	public void deleteBasket(BasketDeleteRequest request, Long userId) {
		// userId와 basketId 유효성 검사
		if (userId == null) {
			throw new IllegalArgumentException("유저 Id가 비어있습니다.");
		}
		if (request.getBasketId() == null) {
			throw new IllegalArgumentException("장바구니 Id가 비어있습니다.");
		}

		// 사용자 존재 여부 확인
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다. Id:" + userId));

		// 장바구니 항목 존재 여부 확인
		Basket basket = basketRepository.findById(request.getBasketId())
				.orElseThrow(() -> new NotFoundException("장바구니 항목이 존재하지 않습니다. Id: " + request.getBasketId()));

		// 장바구니 항목이 해당 사용자 소유인지 확인
		if (!basket.getUser().getId().equals(userId)) {
			throw new IllegalArgumentException("해당 장바구니 항목은 해당 사용자의 것이 아닙니다.");
		}

		// 장바구니 항목 삭제
		basketRepository.delete(basket);
	}
}