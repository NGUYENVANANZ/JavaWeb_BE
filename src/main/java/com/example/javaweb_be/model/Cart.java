package com.example.javaweb_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private boolean status;
}
