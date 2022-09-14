package com.example.mock2project.dto;

import com.example.mock2project.Entity.User;
import com.example.mock2project.Entity.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@NoArgsConstructor
@Getter @Setter
public class UserDetailDTO {

    private Long id;
    private String email;
    private String username;
    private String fullname;
    private LocalDate dob;
    private String address;
    private String gender;
    private User user;


    public UserDetailDTO(String email, String username, String address, LocalDate date_of_birth, String fullname, String gender) {
        this.email = email;
        this.username = username;
        this.address = address;
        this.dob = date_of_birth;
        this.fullname = fullname;
        this.gender = gender;
    }
}
