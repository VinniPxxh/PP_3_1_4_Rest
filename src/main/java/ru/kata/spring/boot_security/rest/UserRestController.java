package ru.kata.spring.boot_security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.service.UserService;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/restuser")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userpage")
    public HttpStatus oneUser(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.findByUsername(principal.getName()));
        return HttpStatus.FOUND;
    }
}
