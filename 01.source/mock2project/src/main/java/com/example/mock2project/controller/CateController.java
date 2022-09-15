package com.example.mock2project.controller;

import com.example.mock2project.dto.CateDTO;
import com.example.mock2project.service.CateService;
import com.example.mock2project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CateController {
    @Autowired
    FileService fileService;
    @Autowired
    CateService cateService;
    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('ROLE_SALE_ADMIN')")
    public ResponseEntity<String> addCate(@RequestParam("file") MultipartFile multipartFile,
                                          @RequestParam("name") String name) throws Exception{
        String link = fileService.upload(multipartFile);
        CateDTO cateDTO = new CateDTO();
        cateDTO.setCate_name(name);
        cateDTO.setImage_link(link);
        cateService.saveCate(cateDTO);
        return new ResponseEntity<>("Add Category "+name+" success", HttpStatus.CREATED);
    }

    @PostMapping("/categories/{id}/update")
    public ResponseEntity<String> updateName(@RequestParam("name") String name,
                                             @PathVariable Long id) throws Exception {
        cateService.updateCateName(id, name);
        return new ResponseEntity<>("Change name for category "+id, HttpStatus.OK);
    }
}
