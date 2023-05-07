package org.example.mongodb.with.spec.api;

import lombok.Builder;

@Builder
public record UserRequest(String firstName, String lastName, String address, Integer index) {}
