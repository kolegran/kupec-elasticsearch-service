package com.cherkassydevelopment.elasticsearch.product;

final class ProductIndexAttributes {

    static final String INDEX_NAME = "products";
    static final short SHARDS = 10;
    static final short REPLICAS = 0;

    private ProductIndexAttributes() {
        // do nothing
    }
}
