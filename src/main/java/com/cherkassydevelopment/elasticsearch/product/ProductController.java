package com.cherkassydevelopment.elasticsearch.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cherkassydevelopment.elasticsearch.product.ProductApiMappings.SAVE_PRODUCTS_MAPPING;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/index")
    public ResponseEntity<Void> createIndex(@RequestParam String indexName) {
        productService.createIndex(indexName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(SAVE_PRODUCTS_MAPPING)
    public ResponseEntity<Void> saveProducts(@RequestBody List<Product> products) {
        productService.saveAllProducts(products);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public List<Product> searchProducts(@RequestParam String indexName, @RequestParam String field, @RequestParam String text) {
        return productService.getProductsByFieldAndText(indexName, field, text);
    }
}
