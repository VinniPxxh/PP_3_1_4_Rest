package ru.kata.spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
