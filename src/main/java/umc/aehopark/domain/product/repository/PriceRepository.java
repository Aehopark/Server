package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
