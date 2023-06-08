package com.example.javaweb_be.controller;

import com.example.javaweb_be.model.Cart;
import com.example.javaweb_be.model.DTO.DetailCartDTO;
import com.example.javaweb_be.model.DetailCart;
import com.example.javaweb_be.model.Product;
import com.example.javaweb_be.repository.CartRepository;
import com.example.javaweb_be.repository.DetailCartRepository;
import com.example.javaweb_be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/detailCart")
@CrossOrigin("*")
public class DetailCartAPI {
    @Autowired
    DetailCartRepository detailCartRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{idCart}/{idProduct}/{amount}")
    public ResponseEntity<DetailCartDTO> addProductToCart(@PathVariable long idCart, @PathVariable long idProduct, @PathVariable int amount) {
        DetailCart detailCart = new DetailCart();
        Cart cart = cartRepository.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        detailCart.setCart(cart);
        detailCart.setProduct(product);
        detailCart.setAmountProduct(amount);
        detailCartRepository.save(detailCart);
        DetailCartDTO detailCartDTO = new DetailCartDTO();
        detailCartDTO.setId(detailCart.getId());
        detailCartDTO.setProductName(detailCart.getProduct().getNameSP());
        detailCartDTO.setImg(detailCart.getProduct().getImg());
        detailCartDTO.setAmountProduct(detailCart.getAmountProduct());
        long price = detailCart.getProduct().getPrice() * detailCart.getAmountProduct();
        detailCartDTO.setPrice(price);
        return new ResponseEntity<>(detailCartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{amount}")
    public ResponseEntity<DetailCartDTO> updateProductAmount(@PathVariable int id, @PathVariable int amount) {
        if (!detailCartRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DetailCart detailCart = detailCartRepository.findById(id).get();
        detailCart.setAmountProduct(amount);
        detailCartRepository.save(detailCart);

        DetailCartDTO detailCartDTO = new DetailCartDTO();
        detailCartDTO.setId(detailCart.getId());
        detailCartDTO.setProductName(detailCart.getProduct().getNameSP());
        detailCartDTO.setImg(detailCart.getProduct().getImg());
        detailCartDTO.setAmountProduct(detailCart.getAmountProduct());
        detailCartDTO.setAmountMax(detailCart.getProduct().getAmount());
        long price = detailCart.getProduct().getPrice() * detailCart.getAmountProduct();
        detailCartDTO.setPrice(price);
        return new ResponseEntity<>(detailCartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable int id) {
        if (!detailCartRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        detailCartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{cartId}")
    public ResponseEntity<List<DetailCartDTO>> getDetailsByCartId(@PathVariable int cartId) {
        List<DetailCart> detailCarts = detailCartRepository.findDetailCartByCart_IdCart(cartId);
        if (detailCarts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DetailCartDTO> detailCartDTOs = new ArrayList<>();
        long price;
        for (DetailCart detailCart : detailCarts) {
            DetailCartDTO detailCartDTO = new DetailCartDTO();
            detailCartDTO.setId(detailCart.getId());
            detailCartDTO.setProductName(detailCart.getProduct().getNameSP());
            detailCartDTO.setImg(detailCart.getProduct().getImg());
            detailCartDTO.setAmountProduct(detailCart.getAmountProduct());
            detailCartDTO.setAmountMax(detailCart.getProduct().getAmount());
            price = detailCart.getProduct().getPrice() * detailCart.getAmountProduct();
            detailCartDTO.setPrice(price);
            detailCartDTOs.add(detailCartDTO);
        }

        return new ResponseEntity<>(detailCartDTOs, HttpStatus.OK);
    }
}
