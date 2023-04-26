package com.andiv.example.springdataelasticsearch.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public abstract class BaseTest {
    @Container
    private static final ElasticsearchContainer elasticsearch = new ElasticsearchTestContainer();

    @BeforeAll
    static void beforeAll() {
        elasticsearch.start();
    }

    @AfterEach
    void tearDown() {
        elasticsearch.stop();
    }
}
