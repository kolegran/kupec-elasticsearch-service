package com.cherkassydevelopment.elasticsearch;

import com.cherkassydevelopment.elasticsearch.es_configuration.ElasticsearchConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
            ElasticsearchConfiguration.class,
            ElasticsearchApplication.class
        )
            .application()
            .run(args);
    }
}
