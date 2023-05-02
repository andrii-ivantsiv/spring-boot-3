package org.example.mongodb.with.spec.api;


import lombok.Builder;

@Builder
public record SearchRequest(String name) {
}
