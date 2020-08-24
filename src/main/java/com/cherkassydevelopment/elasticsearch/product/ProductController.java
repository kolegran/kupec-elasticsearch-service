package com.cherkassydevelopment.elasticsearch.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/index")
    public ResponseEntity<Void> createIndex() {
        productService.createIndex();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/index/name")
    public ResponseEntity<String> getIndexName() {
        // TODO: add tests
        return ResponseEntity.ok(productService.getIndexName());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveProducts() {
        productService.saveAllProducts();
        return ResponseEntity.ok().build();
    }
}
