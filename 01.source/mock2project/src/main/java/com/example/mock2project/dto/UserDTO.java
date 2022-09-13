package com.example.mock2project.dto;

import com.example.mock2project.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private boolean status;
    private Set<Role> roles = new HashSet<>();
}
