package com.example.mock2project.repository;

import com.example.mock2project.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(nativeQuery = true, value = "SELECT * from Rating where user_id=?1 and product_id=?2")
    Rating findByUser_idAndProduct_id(Long user_id, Long product_id);
}
