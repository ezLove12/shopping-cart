package com.example.mock2project.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private String price;


    @Column
    private String image_link;

    @NotBlank
    @Column
    private String description;

    @NotBlank
    @Column
    private Integer quantity;

    @NotBlank
    @Column
    private int status;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @OneToMany(mappedBy = "product")
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "product")
    private List<SizeType> sizeTypes;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;


}
