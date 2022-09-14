package com.example.mock2project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "User_detail")
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    @Size(max = 30)
    private String fullname;

    @Column(name = "dateOfBirth")
    private LocalDate date;

    @Column(name = "address")
    @Size(max = 30)
    private String address;

    @Column(name = "gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
