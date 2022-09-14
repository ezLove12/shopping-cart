package com.example.mock2project.repository;

import com.example.mock2project.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CateRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findByName(String name);
}
