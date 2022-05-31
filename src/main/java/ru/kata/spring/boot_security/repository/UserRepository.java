package ru.kata.spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    @Query("SELECT DISTINCT user FROM User user " +    - не знаю, как это должно работать,
//            "JOIN FETCH user.roles roles")               не могу разобраться.
//    List<User> retrieveAll();

}
