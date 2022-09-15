package com.example.mock2project.service;

import com.example.mock2project.Entity.Categories;
import com.example.mock2project.Entity.Product;
import com.example.mock2project.dto.ProductDTO;
import com.example.mock2project.repository.CateRepository;
import com.example.mock2project.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CateRepository cateRepository;
    public Map<String, Object> getAllProduct(int page, int size) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pagePro = productRepository.findAll(paging);
            List<ProductDTO> productList = new ArrayList<>();
            pagePro.getContent().forEach(product -> {
                productList.add(
                        new ProductDTO(product.getId(), product.getDescription(), product.getImage_link(),
                                product.getName(), product.getPrice(), product.getQuantity(), product.getStatus(),
                                product.getCategories().getName())
                );
            });
            Map<String, Object> response = new HashMap<>();
            response.put("products",productList);
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            response.put("totalPages", pagePro.getTotalPages());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }

    public Map<String, Object> findProductByName(int page, int size, String name) throws Exception {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Product> pagePro = productRepository.findProductByName(name, paging);
            List<ProductDTO> productList = new ArrayList<>();
            pagePro.getContent().forEach(product -> {
                productList.add(
                        new ProductDTO(product.getId(), product.getDescription(), product.getImage_link(),
                                product.getName(), product.getPrice(), product.getQuantity(), product.getStatus(),
                                product.getCategories().getName())
                );
            });
            Map<String, Object> response = new HashMap<>();
            response.put("products", productList);
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }

    public Map<String, Object> findProductByCateIdAndSorting(Long id ,String order, int page, int size) throws Exception {
        try{
            List<String> words = Arrays.stream(order.split("-")).toList();
            Page<Product> pagePro=null;
            if(words.get(0).equals("price")&&(words.get(1).equals("desc")||words.get(1).equals("asc"))){
                Pageable paging = PageRequest.of(page, size);
                if(words.get(1).equals("desc")){
                    pagePro = productRepository.findProductByCateIdAndOrderByPriceDesc(id, words.get(1), paging);
                }else{
                    pagePro = productRepository.findProductByCateIdAndOrderByPriceAsc(id, words.get(1), paging);
                }
            }
            List<ProductDTO> listProduct = new ArrayList<>();
            pagePro.getContent().forEach(p ->{
                listProduct.add(new ProductDTO(p.getId(), p.getDescription(), p.getImage_link(),
                        p.getName(), p.getPrice(), p.getQuantity(), p.getStatus(),
                        p.getCategories().getName()));
            });
            Map<String, Object> response = new HashMap<>();
            response.put("products", listProduct);
            response.put("curPage", pagePro.getNumber());
            response.put("totalPros", pagePro.getTotalElements());
            return response;
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }

    public Product saveProduct(ProductDTO p) throws Exception {
        try{
            Categories categories = cateRepository.findByName(p.getCate_name()).orElseThrow(()->new Exception("Not found categories with name "+p.getCate_name()));
            Product pro = new Product();
            pro.setName(p.getPname());
            pro.setImage_link(p.getImage_link());
            pro.setCategories(categories);
            pro.setPrice(p.getPprice());
            pro.setQuantity(p.getQuantity());
            pro.setPrice(p.getPprice());
            pro.setStatus(p.getStatus());
            return productRepository.save(pro);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
