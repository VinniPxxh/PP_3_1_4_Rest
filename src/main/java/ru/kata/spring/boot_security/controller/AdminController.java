package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

@Controller
@RequestMapping(name = "/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("roles", userService.listRoles());
        model.addAttribute("userMain", user);
        return "adminpage";
    }

    @PostMapping("/adminpage/new")
    public String addUser(User user, @RequestParam("listRoles") long[] role_id) {
        userService.saveUser(user, role_id);
        return "redirect:/api/admin/adminpage";
    }

    @PostMapping("/adminpage/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") long[] role_id) {
        userService.updateUser(user, role_id);
        return "redirect:/api/admin/adminpage";
    }

    @DeleteMapping("/adminpage/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/api/admin/adminpage";
    }
}
