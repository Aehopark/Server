package umc.aehopark.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.user.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
