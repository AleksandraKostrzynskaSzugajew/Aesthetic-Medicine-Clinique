package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {



}
