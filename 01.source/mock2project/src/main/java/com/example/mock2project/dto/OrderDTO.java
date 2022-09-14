package com.example.mock2project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class OrderDTO {
    private Long id;
    private String address;
    private String phone;
    private String totalPrice;
    private Long user_id;
    private LocalDateTime date;
}
