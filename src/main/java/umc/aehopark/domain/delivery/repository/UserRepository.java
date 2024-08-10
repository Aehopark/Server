package umc.aehopark.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllById(Long id);
}
