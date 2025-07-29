package com.example.productService.mapper;

import com.example.productService.dto.ProductDto;
import com.example.productService.model.Product;

/**
 * Mapper to convert Product entity into ProductDto.
 */

public class ProductMapper {
    public static ProductDto toDto(Product p,  int quantity) {
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setQuantity(quantity);
        dto.setInStock(quantity > 0);
        return dto;
    }
}