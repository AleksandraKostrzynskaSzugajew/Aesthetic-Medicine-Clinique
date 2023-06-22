package pl.alekosszu.KME.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.alekosszu.KME.entity.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
