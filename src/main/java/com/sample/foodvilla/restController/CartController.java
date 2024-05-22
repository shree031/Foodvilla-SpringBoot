package com.sample.foodvilla.restController;

import com.sample.foodvilla.entity.CartItem;
import com.sample.foodvilla.entity.domain.DomainCartItem;
import com.sample.foodvilla.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartItemService cartService;

    @PostMapping("/api/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody DomainCartItem cartItemRequest) {
        cartService.addToCart(cartItemRequest.getUserId(), cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/count/{userId}")
    public ResponseEntity<Integer> getCartItemCount(@PathVariable Long userId) {
        int itemCount = cartService.getCartItemCount(userId);
        return new ResponseEntity<>(itemCount, HttpStatus.OK);
    }

    @GetMapping("/api/cart/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @DeleteMapping("/api/cart/{userId}/remove/{productId}")
    public ResponseEntity<String> removeCartItemsByUserId(@PathVariable Long userId, @PathVariable Long productId) {
        boolean isRemoved = cartService.removeCartItem(userId, productId);
        if (isRemoved) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
