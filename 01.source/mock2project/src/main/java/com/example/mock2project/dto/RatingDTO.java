package com.example.mock2project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class RatingDTO {
    private Long id;
    private int vote;
    private Long user_id;
    private Long product_id;

    public RatingDTO(int vote) {
        this.vote = vote;
    }
}
