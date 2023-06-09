package com.example.javaweb_be.controller;

import com.example.javaweb_be.model.Product;
import com.example.javaweb_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductAPI {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<List<Product>> showNewbie() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productRepository.save(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        product.setIdProduct(id);
        Product updatedProduct = productRepository.save(product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        return new ResponseEntity<>(productRepository.findByNameSP(keyword), HttpStatus.OK);

    }
}
