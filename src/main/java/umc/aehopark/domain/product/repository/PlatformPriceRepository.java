package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.PlatformPrice;

public interface PlatformPriceRepository extends JpaRepository<PlatformPrice, Long> {
}
