package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void update(Category author) {
        categoryRepository.save(author);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
