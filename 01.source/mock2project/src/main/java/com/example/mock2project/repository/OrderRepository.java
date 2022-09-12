package com.example.mock2project.repository;

import com.example.mock2project.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "Select * from Order where user_id = ?1")
    List<Order> findByUserId(Long id);

    @Query(nativeQuery = true, value = "SELECT COUNT(order_total_price) from Order where user_id=?1")
    String getPurchasePriceByUserId(Long id);
}
