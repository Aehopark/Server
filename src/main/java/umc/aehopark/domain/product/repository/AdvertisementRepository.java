package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
