package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import javax.management.relation.Role;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
