package com.example.mock2project.repository;

import com.example.mock2project.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategories(Pageable pageable, Long cate_id);

    Page<Product> findAll(Pageable pageable);
}
