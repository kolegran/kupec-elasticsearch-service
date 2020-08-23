package com.cherkassydevelopment.elasticsearch.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/save")
    public ResponseEntity<Product> saveProducts() {
        productService.saveAllProducts();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
