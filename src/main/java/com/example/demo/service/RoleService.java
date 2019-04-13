package com.example.demo.service;

import com.example.demo.model.Role;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    Role getRoleById(String id);
    Role updateRole(Role role);
    List<Role> getAllRoles();
    void deleteRole(String id);
}
