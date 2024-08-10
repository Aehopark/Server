package umc.aehopark.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import umc.aehopark.domain.product.entity.RecommendList;

public interface RecommendListRepository extends JpaRepository<RecommendList, Long> {
	@Query(value = "SELECT * FROM recommend_list ORDER BY RAND() LIMIT 10", nativeQuery = true)
	List<RecommendList> findRandomTop10();
}
