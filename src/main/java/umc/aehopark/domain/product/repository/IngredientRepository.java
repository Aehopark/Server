package umc.aehopark.domain.product.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Category;
import umc.aehopark.domain.product.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	boolean existsByName(String name);

	Optional<Ingredient> findByName(String name);

	Page<Ingredient> findAllByName(PageRequest pageRequest, String name);

	Page<Ingredient> findAllByCategory(PageRequest pageRequest, Category category);

	Page<Ingredient> findAllByCategoryAndName(PageRequest pageRequest, Category category, String name);
}