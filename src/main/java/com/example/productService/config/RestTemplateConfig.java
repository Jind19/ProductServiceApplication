package com.example.productService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration class for defining beans.
 */

@Configuration
public class RestTemplateConfig {

    /**
     * Creates a RestTemplate bean for making HTTP requests to other microservices.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}