package com.example.mock2project.service;

import com.example.mock2project.Entity.Categories;
import com.example.mock2project.repository.CateRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateService {
    @Autowired
    CateRepository cateRepository;

    public Long getCateIdByName(String name){
        Categories c = cateRepository.findByName(name).orElseThrow(()->new ObjectNotFoundException(name, "Not found"));
        return c.getId();
    }
}
