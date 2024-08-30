package ru.kata.spring.boot_security.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;



public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    default void addRole(User user, Role role){
        user.getRoles().add(role);
        save(user);
    }
}
