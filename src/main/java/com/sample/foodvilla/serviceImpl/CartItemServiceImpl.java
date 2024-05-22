package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.CartItem;
import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.repository.CartItemRepository;
import com.sample.foodvilla.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem addToCart(User user, Product product, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {
        CartItem cart = new CartItem(userId, productId, quantity);
        cartItemRepository.save(cart);
    }

    @Override
    public int getCartItemCount(Long userId) {
        return cartItemRepository.countByUserId(userId);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findCartItemsWithProductDetailsByUserId(userId);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
    @Override
    public boolean removeCartItem(Long userId, Long productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        System.err.println("\nuserId"+userId+"\nproductId"+productId);
        if (cartItem != null) {
            System.err.println(cartItem.toString());
            cartItemRepository.delete(cartItem);
            System.err.println("yep delete to hua h");
            return true;
        }
        System.err.println("gadbad ghotala");
        return false;
    }
}
