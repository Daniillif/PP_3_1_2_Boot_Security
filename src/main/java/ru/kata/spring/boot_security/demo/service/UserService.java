package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User saveUser(User userForm);
    void updateUser(User userForm);

    void deleteUser(Long userId);
    List<User> showAllUsers();
    void addRole(User user, Set<Role> roles);
    User findByEmail(String email);
    User getUserById(Long userId);

}