package com.andiv.example.springdataelasticsearch.common;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public class ElasticsearchTestContainer extends ElasticsearchContainer {
    public ElasticsearchTestContainer() {
        super("docker.elastic.co/elasticsearch/elasticsearch:7.17.9");
        this.addFixedExposedPort(9200, 9200);
        this.addFixedExposedPort(9300, 9300);
        this.addEnv("discovery.type", "single-node");
    }
}
