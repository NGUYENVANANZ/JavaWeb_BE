package com.example.javaweb_be.controller;

import com.example.javaweb_be.model.Account;
import com.example.javaweb_be.model.Cart;
import com.example.javaweb_be.repository.AccountRepository;
import com.example.javaweb_be.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartAPI {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    AccountRepository accountRepository;

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCartStatus(@PathVariable Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (!optionalCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cart = optionalCart.get();
        cart.setStatus(false);
        cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> createNewCart(@PathVariable Long id) {
        Account account = accountRepository.findById(id).get();
        Cart cart = new Cart();
        cart.setAccount(account);
        cart.setStatus(true);
        cartRepository.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/buy/{accountId}")
    public ResponseEntity<Optional<Cart>> getCartByAccountIdAndStatus(@PathVariable Long accountId) {
        Optional<Cart> optionalCart = cartRepository.findByAccount_IdAndStatusIsTrue(accountId);

        if (optionalCart.isPresent()) {
            return new ResponseEntity<>(optionalCart, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
