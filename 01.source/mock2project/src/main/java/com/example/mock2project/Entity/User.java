package com.example.mock2project.Entity;

import lombok.Getter;
import lombok.Setter;

import com.example.mock2project.Entity.UserDetail;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String password;

    @NotBlank
    @Column(columnDefinition = "boolean default false")
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public User() {

    }

    public User(String username, String email, String password, boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User(String username, String email, String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
