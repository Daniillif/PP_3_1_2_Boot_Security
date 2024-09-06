package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String show(ModelMap model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles",roleService.getAllRoles());
        model.addAttribute("userFromCH",userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "/admin";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/addRole")
    public String addRole(@ModelAttribute(value = "user") User userForm,@RequestParam(value = "userId") Integer userId) {
        User user = userService.getUserById(userId);
        userService.addRole(user,userForm.getRoles());
        return "redirect:/admin";
    }


    @PostMapping(value = "/delete")
    public String delete( @RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
