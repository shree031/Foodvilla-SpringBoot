package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.ProductDistributor;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    ProductDistributor addProduct(Product product, Long id);

    Product updateProduct(Product product);

    List<Product> getProductsByDistributorId(Long id);
}
