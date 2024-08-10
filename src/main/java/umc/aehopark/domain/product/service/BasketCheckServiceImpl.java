package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import umc.aehopark.domain.product.dto.BasketResponseDto;
import umc.aehopark.domain.product.entity.Basket;
import umc.aehopark.domain.product.repository.BasketRepository;

@Service
@Slf4j
public class BasketCheckServiceImpl implements BasketCheckService {

	@Autowired
	private final BasketRepository basketRepository;

	public BasketCheckServiceImpl(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}

	@Override
	public List<BasketResponseDto> searchBasket(long userId) {
		List<Basket> baskets = basketRepository.findByUserId(userId);
		return baskets.stream().
			map(this::convertToDto).
			collect(Collectors.toList());
	}

	private BasketResponseDto convertToDto(Basket basket) {
		return new BasketResponseDto(
			basket.getId(),
			basket.getUser().getId(),
			basket.getProduct().getId(),
			basket.getQuantity()
		);
	}
}