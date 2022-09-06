package com.example.mock2project.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Size(max = 20)
    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }
}
