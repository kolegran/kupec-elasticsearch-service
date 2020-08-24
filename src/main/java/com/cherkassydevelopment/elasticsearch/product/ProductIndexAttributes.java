package com.cherkassydevelopment.elasticsearch.product;

final class ProductIndexAttributes {
    static final String indexName = "products";
    static final short shards = 10;
    static final short replicas = 0;

    private ProductIndexAttributes() {
        // do nothing
    }
}
