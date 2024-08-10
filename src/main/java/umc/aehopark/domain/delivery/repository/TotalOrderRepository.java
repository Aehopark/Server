package umc.aehopark.domain.delivery.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.payment.entity.TotalOrder;

public interface TotalOrderRepository extends JpaRepository<TotalOrder, Long> {

    Page<TotalOrder> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

}
