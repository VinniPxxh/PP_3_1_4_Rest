package ru.kata.spring.boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user, long[] listRoles);
    void updateUser(User user, long[] listRoles);

    List<User> findAll();

    User getUserById(Long id);

    void deleteById(User user);

    User findByUsername(String username);

    List<Role> listRoles();
    UserDetails loadUserByUsername(String username);

}
