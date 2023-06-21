package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
