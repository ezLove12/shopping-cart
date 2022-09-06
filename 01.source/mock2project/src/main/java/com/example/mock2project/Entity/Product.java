package com.example.mock2project.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "product_name")
    @Size(max = 20)
    private String name;

    @NotBlank
    @Column(name = "product_price")
    private String price;

    @NotBlank
    @Column
    private String image_link;

    @NotBlank
    @Column
    @Size(max = 50)
    private String description;

    @NotBlank
    @Column
    private Integer quantity;

    @NotBlank
    @Column
    private int status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Categories categories;
}
