package com.andiv.example.springdataelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#preface
 */

@EnableAsync
@EnableJpaRepositories(basePackages = "com.andiv.example.springdataelasticsearch.jpa")
@EnableElasticsearchRepositories(basePackages = "com.andiv.example.springdataelasticsearch.repository")
@SpringBootApplication
public class SpringDataElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataElasticsearchApplication.class, args);
    }

}
