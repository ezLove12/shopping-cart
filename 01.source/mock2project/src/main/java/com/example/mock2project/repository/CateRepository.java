package com.example.mock2project.repository;

import com.example.mock2project.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateRepository extends JpaRepository<Categories, Long> {

}
