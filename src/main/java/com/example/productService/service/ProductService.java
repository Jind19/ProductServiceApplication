package com.example.productService.service;

import com.example.productService.dto.ProductDto;
import com.example.productService.mapper.ProductMapper;
import com.example.productService.model.Product;
import com.example.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Business logic service for product management.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final InventoryClient inventoryClient;


    /**
     * Retrieves all products and attaches their live stock quantity.
     */
    public List<ProductDto> getAllProducts(){
        return repo.findAll()
                .stream()
                .map(
                        product -> ProductMapper
                                .toDto( product,
                                        inventoryClient.getStockQuantity(
                                                product.getId()
                                        )
                                )
                )
                .collect(
                        Collectors.toList()
                );
    }

    /**
     * Adds a new product if it's not already present, and creates inventory for it.
     */
    public ProductDto addProduct(Product product, int quantity){
        Optional<Product> existing = repo.findByName(product.getName());
        if (existing.isPresent()) {
            throw new RuntimeException("Product already exists");
        }
        Product savedProduct = repo.save(product);
        inventoryClient.createInventory(savedProduct.getId(), quantity);
        return ProductMapper.toDto(savedProduct, quantity);
    }

}