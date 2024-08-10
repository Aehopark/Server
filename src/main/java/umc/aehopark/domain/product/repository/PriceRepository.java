package umc.aehopark.domain.product.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
	List<Price> findByIngredient(Ingredient ingredient);

	@Query("SELECT MAX(p.price) FROM Price p WHERE p.ingredient.id = :ingredientId")
	BigInteger findMaxPriceByIngredientId(@Param("ingredientId") Long ingredientId);

}
