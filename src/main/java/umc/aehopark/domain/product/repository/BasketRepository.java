package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	List<Basket> findByUserId(Long userId);
}