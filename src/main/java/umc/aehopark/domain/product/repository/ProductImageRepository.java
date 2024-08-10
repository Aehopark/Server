package umc.aehopark.domain.product.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import umc.aehopark.domain.product.entity.Product;
import umc.aehopark.domain.product.entity.ProductImage;

<<<<<<< HEAD
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	// 필요한 메서드들을 정의할 수 있습니다.
	Optional<ProductImage> findByProduct(Product product);
}

=======
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findByProduct(Product product);
}
>>>>>>> c87bb972cc4558dad03ba25f39de87f259b87e38
