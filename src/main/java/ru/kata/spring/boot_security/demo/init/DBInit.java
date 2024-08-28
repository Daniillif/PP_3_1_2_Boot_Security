package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DBInit {
    private final UserService userService;
    RoleDao roleDao;
    UserDao userDao;

    @Autowired
    public DBInit(RoleDao roleDao, UserDao userDao, UserService userService) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userService.saveUser(new User("Ivan1","1111","1111",roleDao.save(new Role(1L, "ADMIN"))));
        userService.saveUser(new User("Ivan2","2222","2222",roleDao.save(new Role(2L, "USER"))));



    }

}