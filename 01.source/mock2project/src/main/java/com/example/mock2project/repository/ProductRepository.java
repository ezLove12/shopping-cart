package com.example.mock2project.repository;

import com.example.mock2project.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategories(Pageable pageable, Long cate_id);

    Page<Product> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "Select * from Product where product_name like %?1%")
    Page<Product> findProductByName(String name, Pageable pageable);
}
