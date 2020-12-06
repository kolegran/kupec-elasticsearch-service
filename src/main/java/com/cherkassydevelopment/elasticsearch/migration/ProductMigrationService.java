package com.cherkassydevelopment.elasticsearch.migration;

import com.cherkassydevelopment.elasticsearch.product.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMigrationService {

    private static final String PRODUCTS_JSON = "products.json";

    public List<Product> parseProductsJson() {
        final List<Product> products = new ArrayList<>();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PRODUCTS_JSON)) {
            final JsonNode jsonNode = new ObjectMapper().readTree(inputStream);
            for (JsonNode node : jsonNode) {
                final Product product = new Product();
                product.setCode(node.get("productCode").asInt());
                product.setName(node.get("name").asText());
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            throw new ProductsJsonParseException("Cannot parse products JSON", e);
        }
    }

    private static final class ProductsJsonParseException extends RuntimeException {
        public ProductsJsonParseException(String message, Exception e) {
            super(message, e);
        }
    }
}
