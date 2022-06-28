package ru.kata.spring.boot_security.repository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.model.User;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u FROM User u join fetch u.roles where u.username =:username")
    User findByUsername(@Param("username") String username);

    default void merge(User user) {
        save(user);
    }
}

