package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Product product);
}
