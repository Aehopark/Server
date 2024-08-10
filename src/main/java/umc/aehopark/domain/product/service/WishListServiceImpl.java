package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umc.aehopark.domain.product.dto.WishListCheckResponseDto;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.entity.WishList;
import umc.aehopark.domain.product.repository.IngredientRepository;
import umc.aehopark.domain.product.repository.WishListRepository;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.domain.user.repository.UserRepository;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {

	private final WishListRepository wishListRepository;

	private final UserRepository userRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	public WishListServiceImpl(WishListRepository wishListRepository, UserRepository userRepository) {
		this.wishListRepository = wishListRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Page<WishListCheckResponseDto> searchWishList(Long userId, Pageable pageable) {
		Page<WishList> wishListPage = wishListRepository.findByUserId(userId, pageable);
		List<WishListCheckResponseDto> wishListCheckResponsesDto = wishListPage.getContent().stream()
			.map(this::convertToDto)
			.collect(Collectors.toList());

		return new PageImpl<>(wishListCheckResponsesDto, pageable, wishListPage.getTotalElements());
	}

	private WishListCheckResponseDto convertToDto(WishList wishList) {
		Ingredient ingredient = wishList.getIngredient();
		Product product =
			ingredient.getProducts().isEmpty() ? null : ingredient.getProducts().get(0); // 예시로 첫 번째 상품을 가져옴
		return new WishListCheckResponseDto(
		);
	}

	@Override
	@Transactional
	public void isWishList(long userId, long ingredientId) {
		WishList newWishList;
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));
		Ingredient ingredient = ingredientRepository.findById(ingredientId)
			.orElseThrow(() -> new RuntimeException("Ingredient not found"));
		newWishList = WishList.builder()
			.user(user)
			.ingredient(ingredient)
			.build();
		// WishList newWishList = new WishList().builder()
		// 	.user(user)
		// 	.ingredient(ingredient)
		// 	.build();
		wishListRepository.save(newWishList);
	}
}

