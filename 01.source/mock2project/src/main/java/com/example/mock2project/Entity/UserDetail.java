package com.example.mock2project.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "User_detail")
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
    private Integer gender;

    @OneToOne(mappedBy = "userDetail")
    private User user;

}
