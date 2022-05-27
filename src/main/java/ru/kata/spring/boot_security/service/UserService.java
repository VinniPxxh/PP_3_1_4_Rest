package ru.kata.spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.repository.UserRepository;

import java.util.List;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addOrUpdateUser(User user) {
        userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public void deleteById(User user) {
        userRepository.delete(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}
