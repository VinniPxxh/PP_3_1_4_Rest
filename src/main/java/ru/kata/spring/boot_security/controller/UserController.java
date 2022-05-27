package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("oneUser", userService.findByName(principal.getName()));
        return "user";
    }

    @GetMapping("/admin_pages")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin_pages/user-list";
    }

    @GetMapping("/admin_pages")
    public String createUserForm(User user) {
        return "/admin_pages/create-user";
    }

    @PostMapping("/admin_pages/user-create")
    public String createUser(User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/admin_pages/user-list";
    }

    @GetMapping(value = "/admin_pages/update-user/{id}")
    public String formUpdateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin_pages/update-user";
    }

    @PatchMapping("/admin_pages/update_user/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addOrUpdateUser(user);
        return "redirect:/admin_pages/user-list";
    }

    @GetMapping(value = "/admin_pages/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/admin_pages/user-list";
    }

    @GetMapping("/admin_pages/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }
}
