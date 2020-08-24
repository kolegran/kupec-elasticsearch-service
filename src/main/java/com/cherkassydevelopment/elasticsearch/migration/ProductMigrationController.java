package com.cherkassydevelopment.elasticsearch.migration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/migrate")
public class ProductMigrationController {
    private final RestTemplate restTemplate;
    private final ProductMigrationService productMigrationService;

    public ProductMigrationController(ProductMigrationService productMigrationService) {
        this.productMigrationService = productMigrationService;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/products")
    public void migrateProducts() {
        // TODO: add context selection dynamically
        final String updateIndexUrl = "http://localhost:8080/api/products/save";
        restTemplate.postForObject(updateIndexUrl, productMigrationService.parseProductsJson(), ResponseEntity.class);
    }
}
