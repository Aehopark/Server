package umc.aehopark.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.aehopark.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findAllById(Long id);
	Optional<User> findByNickname(String nickname);
}
