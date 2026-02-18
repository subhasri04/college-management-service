package in.gopal.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gopal.college.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);
	void deleteByUserId(Long userId);
}
