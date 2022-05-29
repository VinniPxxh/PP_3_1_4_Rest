package ru.kata.spring.boot_security.service;

import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;

import java.util.List;

public interface UserService {
    void addOrUpdateUser(User user);
    List<User> findAll();
    User getUserById(Long id);
    void deleteById(User user);
    User findByUsername(String username);
    public List<Role> listRoles();
}
