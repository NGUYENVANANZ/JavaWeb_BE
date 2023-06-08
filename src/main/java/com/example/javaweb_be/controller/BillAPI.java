package com.example.javaweb_be.controller;

import com.example.javaweb_be.model.Account;
import com.example.javaweb_be.model.Bill;
import com.example.javaweb_be.model.Cart;
import com.example.javaweb_be.model.DTO.BillDTO;
import com.example.javaweb_be.repository.AccountRepository;
import com.example.javaweb_be.repository.BillRepository;
import com.example.javaweb_be.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
@CrossOrigin("*")
public class BillAPI {
    @Autowired
    BillRepository billRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<BillDTO>> getBillsByAccountId(@PathVariable Long accountId) {
        List<Bill> bills = billRepository.findBillByAccount_Id(accountId);
        List<BillDTO> billDTOs = bills.stream().map(bill -> new BillDTO(bill.getId(), bill.getAccount().getName(), bill.getDate(),bill.getCart().getIdCart(), bill.getTotalBill())).collect(Collectors.toList());
        return new ResponseEntity<>(billDTOs, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BillDTO>> getBills() {
        List<Bill> bills = billRepository.findAll();
        List<BillDTO> billDTOs = bills.stream().map(bill -> new BillDTO(bill.getId(), bill.getAccount().getName(), bill.getDate(),bill.getCart().getIdCart(), bill.getTotalBill())).collect(Collectors.toList());
        return new ResponseEntity<>(billDTOs, HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<Bill> createBill(@RequestParam Long idAccount, @RequestParam Long idCart, @RequestParam Long totalBill) {
        Account account = accountRepository.findById(idAccount).orElseThrow(() -> new RuntimeException("Account not found"));
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new RuntimeException("Cart not found"));

        Bill bill = new Bill();
        bill.setAccount(account);
        bill.setCart(cart);
        bill.setDate(LocalDate.now());
        bill.setTotalBill(totalBill);
        billRepository.save(bill);
        return ResponseEntity.ok(bill);
    }
}
