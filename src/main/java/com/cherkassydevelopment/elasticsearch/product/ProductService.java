package com.cherkassydevelopment.elasticsearch.product;

import com.cherkassydevelopment.elasticsearch.elasticsearch.ElasticsearchService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductService {

    private static final long SCROLL_TIME_IN_MILLIS = 1L;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ProductRepository productRepository;
    private final ElasticsearchService elasticsearchService;

    public ProductService(
        ElasticsearchRestTemplate elasticsearchRestTemplate,
        ProductRepository productRepository,
        ElasticsearchService elasticsearchService
    ) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.productRepository = productRepository;
        this.elasticsearchService = elasticsearchService;
    }

    void createIndex(String indexName) {
        elasticsearchService.createIndex(indexName);
    }

    void saveAllProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    List<Product> getProductsByFieldAndText(String indexName, String field, String text) {
        final CriteriaQuery query = new CriteriaQuery(Criteria.where(field).is(text));
        final SearchScrollHits<Product> searchHits = elasticsearchRestTemplate.searchScrollStart(
            SCROLL_TIME_IN_MILLIS, query, Product.class, IndexCoordinates.of(indexName)
        );
        return searchHits.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
    }
}
