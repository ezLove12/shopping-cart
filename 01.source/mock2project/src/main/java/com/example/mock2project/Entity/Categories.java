package com.example.mock2project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categories_id")
    private Long id;

    @Column(name = "cate_name")
    @Size(max = 20)
    private String name;

    @Column(name = "cate_img")
    private String image_category;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> productList;
}
