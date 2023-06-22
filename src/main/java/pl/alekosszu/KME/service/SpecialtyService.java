package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.repository.CategoryRepository;
import pl.alekosszu.KME.repository.SpecialtyRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;


    public void save(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).get();
    }

    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    public void update(Specialty specialty) {
        specialtyRepository.save(specialty);
    }

    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
