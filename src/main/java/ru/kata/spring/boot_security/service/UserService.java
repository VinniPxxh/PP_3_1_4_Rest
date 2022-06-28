package ru.kata.spring.boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    List<User> findAll();

    User getUserById(Long id);

    void deleteById(User user);

    User findByUsername(String username);

    List<Role> listRoles();

    UserDetails loadUserByUsername(String username);

}
