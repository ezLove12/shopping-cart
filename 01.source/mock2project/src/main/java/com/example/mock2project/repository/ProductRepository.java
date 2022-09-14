package com.example.mock2project.repository;

import com.example.mock2project.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategories(Pageable pageable, Long cate_id);

    Page<Product> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "Select * from Product where product_name like %?1%")
    Page<Product> findProductByName(String name, Pageable pageable);


    @Query(nativeQuery = true, value = "Select * from Product where categories_id=?1 order by CAST(product_price as DECIMAL(5,3)) desc")
    Page<Product> findProductByCateIdAndOrderByPriceDesc(Long id, String order, Pageable pageable);

    @Query(nativeQuery = true, value = "Select * from Product where categories_id=?1 order by CAST(product_price as DECIMAL(5,3)) asc")
    Page<Product> findProductByCateIdAndOrderByPriceAsc(Long id, String order, Pageable pageable);


    @Query(nativeQuery = true, value = "Select quantity from Product where id = ?1")
    int getQuantityProduct(Long id);

    @Query(nativeQuery = true, value = "UPDATE Product SET quantity = ?1 WHERE id = ?2")
    void remainQuantityProduct(Integer quantity, Long id);

}
