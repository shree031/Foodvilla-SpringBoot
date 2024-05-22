package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.CartItem;
import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.User;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(User user, Product product, int quantity);

    void addToCart(Long userId, Long productId, int quantity);

    int getCartItemCount(Long userId);

    List<CartItem> getCartItemsByUserId(Long userId);

    void removeCartItem(CartItem cartItem);

    boolean removeCartItem(Long userId, Long productId);
}
