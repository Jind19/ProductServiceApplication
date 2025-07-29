package com.example.productService.repository;

import com.example.productService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing product data in the database.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Checks if a product with the same name already exists.
     */
    Optional<Product> findByName(String name);
}