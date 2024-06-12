package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.ProductDistributor;
import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.repository.ProductDistributorRepository;
import com.sample.foodvilla.repository.ProductRepository;
import com.sample.foodvilla.repository.UserRepository;
import com.sample.foodvilla.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDistributorRepository productDistributorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductDistributor addProduct(Product product, Long id) {
        productRepository.save(product);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User of type 'Distributor' not found"));
        ProductDistributor productDistributor = new ProductDistributor();
        productDistributor.setProduct(product);
        productDistributor.setUser(user);
        return productDistributorRepository.save(productDistributor);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByDistributorId(Long id) {
        List<ProductDistributor> productDistributors = productDistributorRepository.findByUserId(id);
        List<Product> products = new ArrayList<Product>();
        productDistributors.forEach(productDistributor -> {
            products.add(productDistributor.getProduct());
        });
        return products;
    }
}

