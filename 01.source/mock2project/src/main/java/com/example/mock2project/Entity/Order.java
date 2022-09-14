package com.example.mock2project.Entity;

import com.example.mock2project.service.RatingService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
@EntityListeners(RatingService.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "delivery_address")
    private String address;

    @NotBlank
    @Size(max = 15)
    @Column(name = "delivery_phone")
    private String phone;

    @NotBlank
    @Size(max = 10)
    @Column(name = "Order_totalPrice")
    private String totalPrice;

    @Column(name = "order_status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")

    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;
}
