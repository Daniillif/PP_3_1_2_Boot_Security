package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

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
        return "/admin";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") User user,@RequestParam(value = "role") Integer id) {
        Role role = roleService.getRoleById(id);
        user.getRoles().add(role);
        userService.saveUser(user);
        return "redirect:/admin";
    }



    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user,@RequestParam(value = "role1") Integer id) {
        Role role = roleService.getRoleById(id);
        user.getRoles().add(role);
        userService.updateUser(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/addRole")
    public String addRole(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "roleId") Integer roleId) {
        Role role = roleService.getRoleById(roleId);
        User user = userService.getUserById(userId);
        userService.addRole(user,role);
        return "redirect:/admin";
    }


    @PostMapping(value = "/delete")
    public String delete( @RequestParam(value = "id", required = false) Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
