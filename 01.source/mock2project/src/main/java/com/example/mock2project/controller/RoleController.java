package com.example.mock2project.controller;

import com.example.mock2project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class RoleController {
    @Autowired
    RoleService roleService;
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRole(@PathVariable Long id) throws Exception {
        roleService.deleteRole(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Delete role with id ="+id+" Note that user role is deleted too");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
