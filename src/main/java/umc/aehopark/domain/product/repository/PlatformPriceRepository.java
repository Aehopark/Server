package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.PlatformPrice;

public interface PlatformPriceRepository extends JpaRepository<PlatformPrice, Long> {
	List<PlatformPrice> findByIngredient(Ingredient ingredient);
}
