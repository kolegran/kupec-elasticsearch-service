package com.cherkassydevelopment.elasticsearch.product;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public ProductService(ProductRepository productRepository, ElasticsearchOperations elasticsearchOperations) {
        this.productRepository = productRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void saveAllProducts() {
        elasticsearchOperations.indexOps(Product.class);
        final Product product = new Product();
        product.setName("USB Adapter Bluetooth 4.0");
        product.setCode(7947);

        productRepository.save(product);
    }
}
