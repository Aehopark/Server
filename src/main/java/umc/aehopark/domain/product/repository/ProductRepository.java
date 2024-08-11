package umc.aehopark.domain.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByIngredient(Ingredient ingredient);

	@Query("SELECT p FROM Product p WHERE p.ingredient = :ingredient")
	Optional<Product> findByOneIngredient(@Param("ingredient") Ingredient ingredient);

	boolean existsByName(String name);
}

