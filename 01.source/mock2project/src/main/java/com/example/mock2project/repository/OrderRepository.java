package com.example.mock2project.repository;

import com.example.mock2project.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "Select * from Orders where user_id = ?1")
    Page<Order> findByUserId(Long id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT SUM(order_total_price) from Orders where user_id=?1")
    String getPurchasePriceByUserId(Long id);

    @Override
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Long id);

}
