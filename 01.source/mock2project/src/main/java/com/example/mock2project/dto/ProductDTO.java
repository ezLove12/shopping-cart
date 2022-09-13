package com.example.mock2project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String des;
    private String image_link;
    private String pname;
    private String pprice;
    private Integer quantity;
    private int status;
    private String cate_name;
}
