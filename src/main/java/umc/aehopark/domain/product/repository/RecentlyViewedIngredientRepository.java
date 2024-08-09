package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.RecentlyViewedIngredient;

public interface RecentlyViewedIngredientRepository extends JpaRepository<RecentlyViewedIngredient, Long> {
}
