package com.example.javaweb_be.repository;

import com.example.javaweb_be.model.DetailCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailCartRepository extends JpaRepository<DetailCart, Integer> {
    List<DetailCart> findDetailCartByCart_IdCart(long id);

    DetailCart findDetailCartByCart_IdCartAndProductIdProduct(long id_Cart, long id_Product);

}
