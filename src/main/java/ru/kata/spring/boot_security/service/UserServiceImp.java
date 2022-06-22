package ru.kata.spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.repository.RoleRepository;
import ru.kata.spring.boot_security.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUser(User user, long[] listRoles) {
        Set<Role> rolesSet = new HashSet<>();
        for (int i = 0; i < listRoles.length; i++) {
            rolesSet.add(roleRepository.findById(listRoles[i]));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(rolesSet);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void updateUser(User user, long[] role_id) {
        Set<Role> rolesSet = new HashSet<>();
        for (int i = 0; i < role_id.length; i++) {
            rolesSet.add(roleRepository.findById(role_id[i]));
        }
        if (user.getPassword().startsWith("$2a$10$") && user.getPassword().length() == 60) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(rolesSet);
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user" + " " + username);
        }
        return user;
    }
}
