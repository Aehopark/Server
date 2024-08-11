package umc.aehopark.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umc.aehopark.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByNickname(String nickname);

	User findAllById(Long id);

	Optional<User> findByNickname(String nickname);

	boolean existsByProviderId(String providerId);
}

