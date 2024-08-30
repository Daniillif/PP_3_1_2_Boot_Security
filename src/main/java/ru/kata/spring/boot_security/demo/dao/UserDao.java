package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;


public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    default void addRole(User user, Set<Role> roles){
        user.getRoles().addAll(roles);
        save(user);
    }

}
