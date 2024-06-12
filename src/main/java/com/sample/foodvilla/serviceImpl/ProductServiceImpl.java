package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.ProductDistributor;
import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.model.ProductStatus;
import com.sample.foodvilla.repository.ProductDistributorRepository;
import com.sample.foodvilla.repository.ProductRepository;
import com.sample.foodvilla.repository.UserRepository;
import com.sample.foodvilla.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void deleteProduct(Long productId) {
        // Delete the related entries in product_distributor first
        productDistributorRepository.deleteByProductId(productId);

        // Then set the status to DELETED
        updateProductStatus(productId, ProductStatus.DELETED);
    }

    @Transactional
    public void markProductAsOutOfStock(Long productId) {
        updateProductStatus(productId, ProductStatus.OUT_OF_STOCK);
    }

    @Transactional
    public void markProductAsAvailable(Long productId) {
        updateProductStatus(productId, null);
    }

    private void updateProductStatus(Long productId, ProductStatus status) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStatus(status);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }


}

