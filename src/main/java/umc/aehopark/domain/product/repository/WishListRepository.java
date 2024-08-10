package umc.aehopark.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
