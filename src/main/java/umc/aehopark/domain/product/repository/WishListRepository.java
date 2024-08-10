package umc.aehopark.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umc.aehopark.domain.product.entity.Ingredient;
import umc.aehopark.domain.product.entity.WishList;
import umc.aehopark.domain.user.entity.User;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
	Page<WishList> findByUserId(Long userId, Pageable pageable);

	boolean existsByUserAndIngredient(User user, Ingredient ingredient);
}

