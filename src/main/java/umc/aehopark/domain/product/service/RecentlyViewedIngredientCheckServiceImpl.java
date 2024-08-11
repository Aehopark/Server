package umc.aehopark.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import umc.aehopark.domain.product.dto.RecentProductResponseDto;
import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.repository.IngredientImageRepository;
import umc.aehopark.domain.product.repository.ProductImageRepository;
import umc.aehopark.domain.product.repository.ProductRepository;
import umc.aehopark.domain.product.repository.RecentlyViewedIngredientRepository;
import umc.aehopark.domain.product.repository.WishListRepository;
import umc.aehopark.domain.user.entity.User;
import umc.aehopark.domain.user.repository.UserRepository;

@Service
public class RecentlyViewedIngredientCheckServiceImpl implements RecentlyViewedIngredientCheckService {

	private final RecentlyViewedIngredientRepository recentlyViewedIngredientRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final ProductImageRepository productImageRepository;
	private final WishListRepository wishListRepository;
	private final IngredientImageRepository ingredientImageRepository;

	@Autowired
	public RecentlyViewedIngredientCheckServiceImpl(
		RecentlyViewedIngredientRepository recentlyViewedIngredientRepository, UserRepository userRepository,
		ProductRepository productRepository, ProductImageRepository productImageRepository,
		WishListRepository wishListRepository, IngredientImageRepository ingredientImageRepository) {
		this.recentlyViewedIngredientRepository = recentlyViewedIngredientRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.productImageRepository = productImageRepository;
		this.wishListRepository = wishListRepository;
		this.ingredientImageRepository = ingredientImageRepository;
	}

	@Override
	public List<RecentProductResponseDto> searchRecentProduct(Long userId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));
		Page<Ingredient> recentProducts = recentlyViewedIngredientRepository.findRecentIngredientsByUserId(userId,
			pageRequest);

		List<RecentProductResponseDto> results = recentProducts.getContent()
			.stream()
			.map(RecentProductResponseDto::new)
			.collect(Collectors.toList());

		return results;
	}

}
