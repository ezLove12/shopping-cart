package com.example.mock2project.dto;

import com.example.mock2project.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {

    private Long id;
    private Integer productQuantity;
    private Product product;
    private String productTotalPrice;

}
