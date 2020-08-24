package com.cherkassydevelopment.elasticsearch.elasticsearch;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ElasticsearchController {
    private final ElasticsearchService elasticsearchService;

    public ElasticsearchController(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @DeleteMapping("index/{name}")
    public void removeIndexByName(@PathVariable String name) {
        boolean deleted = elasticsearchService.removeIndexByName(name);
        // TODO: add logging
    }
}
