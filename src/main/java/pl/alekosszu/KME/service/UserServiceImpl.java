package pl.alekosszu.KME.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.alekosszu.KME.repository.RoleRepository;
import pl.alekosszu.KME.repository.UserRepository;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.HashSet;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
