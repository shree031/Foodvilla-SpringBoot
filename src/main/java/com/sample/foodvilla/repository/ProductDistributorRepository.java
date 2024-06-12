package com.sample.foodvilla.repository;

import com.sample.foodvilla.entity.ProductDistributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDistributorRepository extends JpaRepository<ProductDistributor, Long> {
    List<ProductDistributor> findByUserId(Long userId);
}