package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Repository
public interface RoleRepository {

    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(int id);

    void addRole(Role role);
}
