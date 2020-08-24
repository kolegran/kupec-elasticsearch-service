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

    public List<Product> parseProductsJson() {
        final List<Product> products = new ArrayList<>();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json")) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final JsonNode jsonNode = objectMapper.readTree(inputStream);
            for (JsonNode node : jsonNode) {
                final Product product = new Product();
                product.setCode(node.get("productCode").asInt());
                product.setName(node.get("name").asText());
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            // TODO: handle exception
            throw new JsonParseCustomException("Cannot parse JSON", e);
        }
    }

    private static class JsonParseCustomException extends RuntimeException {
        public JsonParseCustomException(String message, Exception e) {
            super(message, e);
        }
    }
}
