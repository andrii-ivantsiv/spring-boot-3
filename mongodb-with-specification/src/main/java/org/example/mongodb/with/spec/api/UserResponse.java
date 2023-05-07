package org.example.mongodb.with.spec.api;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

@Builder
public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        String address,
        Integer index,
        Instant createdAt,
        Instant updatedAt) {}
