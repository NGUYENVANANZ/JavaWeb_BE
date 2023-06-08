package com.example.javaweb_be.model.DTO;

import lombok.Data;

@Data
public class DetailCartDTO {
    private int id;
    private String productName;
    private String img;
    private int amountProduct;
    private Long amountMax;
    private Long price;
}
