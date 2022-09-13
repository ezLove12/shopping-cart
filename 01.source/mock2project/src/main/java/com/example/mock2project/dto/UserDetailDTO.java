package com.example.mock2project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserDetailDTO {
    private Long id;
    private String email;
    private String username;
    private String fullname;
    private LocalDate dob;
    private String address;
    private String gender;
}
