package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findByProduct(Product product);
	//Optional<ProductImage> findByProduct(Product product);
}

