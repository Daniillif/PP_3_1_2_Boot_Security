package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User userForm);
    void updateUser(User userForm);

    void deleteUser(Long userId);
    List<User> allUsers();
//    void create(User user);
//    void update(User user);
//    void delete(long id);
//    List<User> show();
}