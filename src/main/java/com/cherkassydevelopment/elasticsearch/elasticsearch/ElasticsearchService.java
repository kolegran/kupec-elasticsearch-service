package com.cherkassydevelopment.elasticsearch.elasticsearch;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void createIndex(Class<?> c) {
        elasticsearchOperations.indexOps(c);
    }

    public String getIndexName(Class<?> c) {
        return elasticsearchOperations.getIndexCoordinatesFor(c).getIndexName();
    }

    public boolean removeIndexByName(String name) {
        return elasticsearchOperations.indexOps(IndexCoordinates.of(name)).delete();
    }
}
