package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Component
public class DBInit {
    RoleDao roleDao;
    UserDao userDao;

    @Autowired
    public DBInit(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @PostConstruct
    public void init() {
        roleDao.save(new Role(1L, "ADMIN"));
        roleDao.save(new Role(2L, "USER"));
        userDao.save(new User("admin","admin","admin",roleDao.getById(1L)));
        userDao.save(new User("user","2222","2222",roleDao.getById(2L)));
        userDao.save(new User("user2","3333","3333",roleDao.getById(2L)));
        userDao.save(new User("user3","4444","4444",roleDao.getById(2L)));


    }

}
