package pl.alekosszu.KME.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alekosszu.KME.entity.treatments.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {



}
