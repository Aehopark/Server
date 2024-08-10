package umc.aehopark.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
}
