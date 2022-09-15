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

    public ProductDTO(String des, String pname, String pprice, Integer quantity, int status, String cate_name) {
        this.des = des;
        this.pname = pname;
        this.pprice = pprice;
        this.quantity = quantity;
        this.status = status;
        this.cate_name = cate_name;
    }
}
