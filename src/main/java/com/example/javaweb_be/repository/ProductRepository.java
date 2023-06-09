package com.example.javaweb_be.repository;

import com.example.javaweb_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM product WHERE namesp LIKE concat('%',:name,'%') ")
    List<Product> findByNameSP( String name);
}
