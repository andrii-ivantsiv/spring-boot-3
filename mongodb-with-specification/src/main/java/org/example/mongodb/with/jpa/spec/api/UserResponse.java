package org.example.mongodb.with.jpa.spec.api;

import java.util.UUID;

public record UserResponse(UUID id, String name) {
}
