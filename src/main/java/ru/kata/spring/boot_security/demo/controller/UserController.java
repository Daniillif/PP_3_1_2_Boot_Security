package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(ModelMap model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userFromCH",userService.loadUserByUsername(username));
        return "user";
    }

//


}
