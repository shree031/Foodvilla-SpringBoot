package com.sample.foodvilla.repository;

import com.sample.foodvilla.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Add custom methods if needed
    int countByUserId(Long userId);

    List<CartItem> findByUserId(Long userId);

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product p WHERE ci.user.id = :userId")
    List<CartItem> findCartItemsWithProductDetailsByUserId(Long userId);
    CartItem findByUserIdAndProductId(Long userId, Long productId);
}
