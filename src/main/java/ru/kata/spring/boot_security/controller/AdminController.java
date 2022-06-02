package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.Role;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(name = "/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/admin/adminpage")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("roles", userService.listRoles());
        model.addAttribute("userMain", user);
        return "adminpage";
    }

    @PostMapping("/api/admin/adminpage/new")
    public String addUser(User user, @RequestParam("listRoles") Set<Role> roles) {
        userService.addOrUpdateUser(user, roles);
        return "redirect:/api/admin/adminpage";
    }

    @PostMapping("/api/admin/adminpage/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") Set<Role> roles) {
        userService.addOrUpdateUser(user, roles);
        return "redirect:/api/admin/adminpage";
    }

    @RequestMapping(value = "/api/admin/adminpage/delete/{id}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/api/admin/adminpage";
    }
}
