package org.example.mongodb.with.spec.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@Document
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

}
