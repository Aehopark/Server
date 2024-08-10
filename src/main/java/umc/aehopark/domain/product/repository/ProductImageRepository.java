package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findByProduct(Product product);
}
