package ru.kata.spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @Autowired
    public UserController() {}

    @GetMapping("/userpage")
    public String getUser() {
        return "userpage";
    }
}
