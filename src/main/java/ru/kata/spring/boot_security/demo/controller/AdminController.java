package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String show(ModelMap model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "/admin";
    }

    @PostMapping(value = "/admin/create")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }



    @PostMapping(value = "/admin/update")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/delete")
    public String delete( @RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
