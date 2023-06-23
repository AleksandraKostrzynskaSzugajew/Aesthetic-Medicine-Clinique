package pl.alekosszu.KME.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.repository.RoleRepository;
import pl.alekosszu.KME.repository.SpecialtyRepository;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public void save(Role role) {
        roleRepository.save(role);
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }


    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }


}
