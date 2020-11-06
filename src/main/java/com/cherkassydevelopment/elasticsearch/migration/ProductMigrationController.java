package com.cherkassydevelopment.elasticsearch.migration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.cherkassydevelopment.elasticsearch.product.ProductApiMappings.SAVE_PRODUCTS_MAPPING;

@RestController
@RequestMapping("/migration")
public class ProductMigrationController {

    private static final String SERVLET_CONTEXT_PATH = "/api";
    private final RestTemplate restTemplate;
    private final ProductMigrationService productMigrationService;
    private final String serverContext;

    public ProductMigrationController(
        ProductMigrationService productMigrationService,
        @Value("${server.context}") String serverContext
    ) {
        this.productMigrationService = productMigrationService;
        this.serverContext = serverContext;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/products")
    public void migrateProducts() {
        final String updateIndexUrl = serverContext + SERVLET_CONTEXT_PATH + SAVE_PRODUCTS_MAPPING;
        restTemplate.postForObject(updateIndexUrl, productMigrationService.parseProductsJson(), ResponseEntity.class);
    }
}
