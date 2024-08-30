package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public DBInit( UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role admin = roleService.saveRole(new Role("ADMIN"));
        Role user = roleService.saveRole(new Role("USER"));
        User user1 = userService.saveUser(new User("Ivan1","1111","1111",roleService.saveRole(admin)));
        User user2 =userService.saveUser(new User("Ivan2","2222","2222",roleService.saveRole(user)));
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        userService.addRole(user2,roles);




    }

}