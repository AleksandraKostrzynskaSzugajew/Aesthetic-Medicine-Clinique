package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.repository.UserRepository;
import pl.alekosszu.KME.security.MyPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final MyPasswordEncoder myPasswordEncoder;
    private final RoleService roleService;


    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }


    public void registerNewUser(User user) {
        user.setEmail(user.getEmail());
        user.setPassword(myPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

}
