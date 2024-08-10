package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}