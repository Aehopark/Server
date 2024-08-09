package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	boolean existsByName(String name);
}
