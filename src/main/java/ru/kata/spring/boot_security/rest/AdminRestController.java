package ru.kata.spring.boot_security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restadmin")
public class AdminRestController {
    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public ResponseEntity<List<User>> userList() {
        final List<User> users = userService.findAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/adminpage/new")
    public ResponseEntity<?> addUser(@RequestBody User user, @RequestParam("listRoles") long[] role_id) {
        userService.saveUser(user, role_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/adminpage/edit")
    public ResponseEntity<?> update(@RequestBody User user, @RequestParam("listRoles") long[] role_id) {
        userService.updateUser(user, role_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/adminpage/{id}")
    public HttpStatus removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return HttpStatus.GONE;
    }

}
