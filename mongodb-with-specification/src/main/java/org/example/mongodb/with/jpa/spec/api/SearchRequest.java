package org.example.mongodb.with.jpa.spec.api;


import lombok.Builder;

@Builder
public record SearchRequest(String name) {
}
