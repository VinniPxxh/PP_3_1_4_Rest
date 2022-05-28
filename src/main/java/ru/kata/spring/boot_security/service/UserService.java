package ru.kata.spring.boot_security.service;

import ru.kata.spring.boot_security.model.User;

import java.util.List;

public interface UserService {
    void addOrUpdateUser(User user);
    List<User> findAll();
    User getUserById(Long id);
    void deleteById(User user);
    User findByName(String name);

}
