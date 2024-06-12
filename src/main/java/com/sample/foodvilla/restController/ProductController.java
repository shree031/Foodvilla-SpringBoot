package com.sample.foodvilla.restController;

import com.sample.foodvilla.entity.Product;
import com.sample.foodvilla.entity.ProductDistributor;
import com.sample.foodvilla.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/distributor/{id}")
    public ResponseEntity<List<Product>> getDistributorProduct(@PathVariable Long id) {
        List<Product> products = productService.getProductsByDistributorId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new product
    @PostMapping("/{id}")
    public ResponseEntity<ProductDistributor> addProduct(@PathVariable Long id, @RequestBody Product product) {
        ProductDistributor savedProduct = productService.addProduct(product, id);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            product.setId(id); // Ensure the ID is set
            Product updatedProduct = productService.updateProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productId}/outOfStock")
    public ResponseEntity<?> markProductAsOutOfStock(@PathVariable Long productId) {
        productService.markProductAsOutOfStock(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/available")
    public ResponseEntity<?> markProductAsAvailable(@PathVariable Long productId) {
        productService.markProductAsAvailable(productId);
        return ResponseEntity.ok().build();
    }
}
