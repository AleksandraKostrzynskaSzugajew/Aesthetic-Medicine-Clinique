package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.user.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
