package umc.aehopark.domain.product.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.RecentlyViewedIngredient;

public interface RecentlyViewedIngredientRepository extends JpaRepository<RecentlyViewedIngredient, Long> {
	@Query("SELECT r.ingredient FROM RecentlyViewedIngredient r WHERE r.user.id = :userId ORDER BY r.createdAt DESC")
	Page<Ingredient> findRecentIngredientsByUserId(Long userId, Pageable pageable);

	Optional<Ingredient> findByIngredient(Ingredient ingredient);

	//Page<Ingredient> findRecentIngredientsByUserId(User user, Pageable pageRequest);
}
