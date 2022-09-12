package com.example.mock2project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class OrderDetailsDTO {

    private Long id;

    private Integer productQuantity;

    private String productTotalPrice;

    private Long order_id;

    private Long product_id;
}
