package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role saveRole(Role role) {
        roleDao.save(role);
        return role;
    }

    @Override
    public void deleteRole(Role role) {
        roleDao.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public void updateRole(Role role) {
        roleDao.save(role);
    }
    public Role getRoleById(int id) {
        return roleDao.getById((long) id);
    }
}
