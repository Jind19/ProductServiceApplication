package com.example.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Component for calling the external inventory-service.
 */
@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Value("${INVENTORY_SERVICE_URL}")
    private String inventoryUrl;

    /**
     * Retrieves stock quantity for a product from the inventory-service.
     */
    public int getStockQuantity(Long productId){
        Integer quantity = restTemplate.getForObject(
                inventoryUrl+"/"+productId, Integer.class
        );
        return quantity != null ? quantity : 0;
    }

    /**
     * Sends a POST request to create or update inventory for a product.
     */
    public void createInventory(Long productId, int quantity) {
        restTemplate.postForObject(
                inventoryUrl, new InventoryRequest(productId, quantity), Void.class);
    }

    /**
     * Internal request body for creating inventory.
     */
    public record InventoryRequest(Long productId, int quantity) {}

}