package com.example.productService.dto;

import lombok.Data;


/**
 * DTO (Data Transfer Object) for exposing product data with stock quantity.
 */

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;

    private int quantity;
    private boolean inStock;
}