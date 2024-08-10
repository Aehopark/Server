package umc.aehopark.domain.delivery.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.payment.entity.OrderDetail;
import umc.aehopark.domain.payment.entity.TotalOrder;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Page<OrderDetail> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
