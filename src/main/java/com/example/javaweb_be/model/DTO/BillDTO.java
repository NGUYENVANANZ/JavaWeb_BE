package com.example.javaweb_be.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BillDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private Long id_Cart;
    private Long totalBill;

    public BillDTO(Long id, String name, LocalDate date, Long id_Cart, Long totalBill) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.id_Cart = id_Cart;
        this.totalBill = totalBill;
    }
}
