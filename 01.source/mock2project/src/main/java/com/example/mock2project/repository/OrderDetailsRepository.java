package com.example.mock2project.repository;

import com.example.mock2project.Entity.OrderDetails;
import com.example.mock2project.dto.OrderDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query("Select od from OrderDetails od where od.order.id= ?1")
    List<OrderDetails> findByOrder(Long id);
}
