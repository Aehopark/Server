package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umc.aehopark.domain.product.entity.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
	@Query("SELECT a FROM Advertisement a ORDER BY a.createdAt ASC")
	List<Advertisement> findTop10ByOrderByCreatedAtAsc();
}
