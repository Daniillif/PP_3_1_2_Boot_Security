package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerApi {
    private UserService userService;

    @Autowired
    public RestControllerApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.showAllUsers();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody User user) {
         userService.updateUser(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete( @PathVariable Long id) {
         userService.deleteUser(id);
    }
}
