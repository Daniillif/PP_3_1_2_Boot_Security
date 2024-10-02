package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    void deleteRole(Role role);
    List<Role> getAllRoles();
    void updateRole(Role role);
    Role getRoleById(Long id);
}

