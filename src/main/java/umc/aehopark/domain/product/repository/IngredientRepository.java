package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}