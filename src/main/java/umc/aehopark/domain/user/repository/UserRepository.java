package umc.aehopark.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.user.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByNickname(String nickname);
	User findAllById(Long id);
	Optional<User> findByNickname(String nickname);

	boolean existsByProviderId(String providerId);
}
