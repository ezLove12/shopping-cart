package com.example.mock2project.service;

import com.example.mock2project.Entity.Categories;
import com.example.mock2project.dto.CateDTO;
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

    public Categories saveCate(CateDTO cateDTO) throws Exception {

        Categories c = new Categories();
        c.setName(cateDTO.getCate_name());
        c.setImage_category(cateDTO.getImage_link());
        return cateRepository.save(c);
    }

    public void updateCateName(Long id, String newname) throws Exception {
        Categories cate = cateRepository.findById(id).orElseThrow(()-> new Exception("Not found category "));
        cate.setName(newname);
        cateRepository.save(cate);
    }
}
