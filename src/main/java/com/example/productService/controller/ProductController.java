package com.example.productService.controller;

/**
 * REST controller exposing product-related HTTP endpoints.
 */

import com.example.productService.dto.ProductDto;
import com.example.productService.model.Product;
import com.example.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    /**
     * GET /products — returns list of all products with live stock data.
     */
    @GetMapping
    public List<ProductDto> getAll() {

        return service.getAllProducts();
    }

    /**
     * POST /products — adds a new product and initializes its inventory.
     */
    @PostMapping
    public ProductDto add(@RequestBody ProductWithQuantity request){
        return service.addProduct(request.product(), request.quantity());
    }


    /**
     * Request object for adding product with initial quantity.
     *
     * @param product  product entity
     * @param quantity quantity to initialize
     */
    public record ProductWithQuantity(Product product, int quantity) {}

}