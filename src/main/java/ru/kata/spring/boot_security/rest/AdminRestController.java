package ru.kata.spring.boot_security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.model.User;
import ru.kata.spring.boot_security.service.UserService;

@RestController
@RequestMapping(name = "/api/restadmin")
public class AdminRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/api/restadmin/adminpage")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("roles", userService.listRoles());
        model.addAttribute("userMain", user);
        return "adminpage";
    }

    @PostMapping("/api/restadmin/adminpage/new")
    public String addUser(User user, @RequestParam("listRoles") long[] role_id) {
        userService.saveUser(user, role_id);
        return "redirect:/api/admin/adminpage";
    }

    @PutMapping("/api/restadmin/adminpage/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") long[] role_id) {
        userService.updateUser(user, role_id);
        return "redirect:/api/restadmin/adminpage";
    }

    @DeleteMapping("/api/restadmin/adminpage/delete/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.deleteById(userService.getUserById(id));
        return "redirect:/api/restadmin/adminpage";
    }
}
