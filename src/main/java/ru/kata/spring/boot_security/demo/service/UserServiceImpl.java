package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public List<User> showAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
        return user;
    }
    @Override
    @Transactional
    public void addRole(User user, Set<Role> roles) {
        user.getRoles().addAll(roles);
        userDao.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getById(userId);
    }


    @Transactional
    @Override
    public void updateUser(User user) {
        if (userDao.getById(user.getId()) == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (userDao.findById(userId).isPresent()) {
            userDao.deleteById(userId);

        }else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userDao.findUserByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: '%s' not found", email)));
    }
}


