package com.example.javaweb_be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    private String nameSP;

    private Long price;

    private String img;

    private Long amount;

    private Long productType;

}