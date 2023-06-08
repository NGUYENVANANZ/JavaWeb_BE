package com.example.javaweb_be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DetailCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int amountProduct;

}
