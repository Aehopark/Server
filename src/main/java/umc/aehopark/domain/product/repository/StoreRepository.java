package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
