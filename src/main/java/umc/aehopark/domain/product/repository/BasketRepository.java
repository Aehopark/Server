package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umc.aehopark.domain.product.entity.Basket;
import umc.aehopark.domain.user.entity.User;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	@Query("SELECT b FROM Basket b WHERE b.user = :user")
	List<Basket> findByUser(User user);
}
