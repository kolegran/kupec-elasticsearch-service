package com.cherkassydevelopment.elasticsearch.product;

import com.cherkassydevelopment.elasticsearch.elasticsearch.ElasticsearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchService elasticsearchService;

    public ProductService(ProductRepository productRepository, ElasticsearchService elasticsearchService) {
        this.productRepository = productRepository;
        this.elasticsearchService = elasticsearchService;
    }

    public void createIndex() {
        elasticsearchService.createIndex(Product.class);
    }

    public String getIndexName() {
        return elasticsearchService.getIndexName(Product.class);
    }

    public void saveAllProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}
