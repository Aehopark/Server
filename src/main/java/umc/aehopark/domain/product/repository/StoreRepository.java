package umc.aehopark.domain.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Store;
import umc.aehopark.domain.user.entity.User;

public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByUser(User user);
}
