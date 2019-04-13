package com.example.demo.controller;

import com.example.demo.dto.RoleDto;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private RoleService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public RoleDto getRoleById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getRoleById(id), RoleDto.class);
    }

    @GetMapping(value = "/all")
    public List<RoleDto> getAllRoles() {
        List<RoleDto> rolesDto = new ArrayList<>();
        List<Role> roles = service.getAllRoles();
        for(Role item : roles) {
            rolesDto.add(modelMapper.map(item, RoleDto.class));
        }
        return rolesDto;
    }

    @PostMapping
    public Role saveRole(@RequestBody Role account) {
        return service.saveRole(account);
    }

    @PutMapping
    public RoleDto updateRole(@RequestBody RoleDto roleForUpdate) {
        Role role = modelMapper.map(service.getRoleById(roleForUpdate.getId()), Role.class);
        return modelMapper.map(service.updateRole(role), RoleDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteRole(@PathVariable(name = "id") String id) {
        service.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
