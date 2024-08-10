package umc.aehopark.domain.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.IngredientImage;

@Repository
public interface IngredientImageRepository extends JpaRepository<IngredientImage, Long> {
	// 필요한 메서드들을 정의할 수 있습니다.
	Optional<IngredientImage> findByIngredient(Ingredient ingredient);

	boolean existsByIngredient(Ingredient ingredient);
}

