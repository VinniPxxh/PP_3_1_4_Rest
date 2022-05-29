package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String oneUser(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.findByUsername(principal.getName()));
        return "user";
    }

    @GetMapping(value = "/user-list")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping(value = "/create-user")
    public String createUserForm(Model model,User user) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping(value = "/user-create")
    public String createUser(User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/user-list";
    }

    @GetMapping(value = "/update-user/{id}")
    public String formUpdateUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "update-user";
    }

    @PostMapping(value = "/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/user-list";
    }

    @GetMapping(value = "/user-delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/user-list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }
}
