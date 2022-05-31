package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(name = "/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/admin/user-list")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/api/admin/create-user")
    public String createUserForm(@ModelAttribute("user") User user, Model model) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "create-user";
    }

    @PostMapping("/api/admin/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/api/admin/user-list";
    }

    @GetMapping(value = "/api/admin/update-user/{id}")
    public String formUpdateUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "update-user";
    }

    @PostMapping(value = "/api/admin/update-user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/api/admin/user-list";
    }

    @GetMapping(value = "/api/admin/user-delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/api/admin/user-list";
    }

    @GetMapping("/api/admin/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userpage";
    }
}
