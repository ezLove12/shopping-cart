package com.example.mock2project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfo {

    private String email;
    private String username;
    private String address;
    private LocalDate date_of_birth;
    private String fullname;
    private String gender;

    public UserInfo() {
    }

    public UserInfo(String email, String username, String address, LocalDate date_of_birth, String fullname, String gender) {
        this.email = email;
        this.username = username;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.fullname = fullname;
        this.gender = gender;
    }
}
