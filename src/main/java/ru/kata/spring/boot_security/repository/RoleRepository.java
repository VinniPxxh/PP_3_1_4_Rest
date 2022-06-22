package ru.kata.spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.model.Role;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findById(long id);
}
