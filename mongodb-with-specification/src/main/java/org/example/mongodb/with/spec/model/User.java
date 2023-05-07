package org.example.mongodb.with.spec.model;

import com.querydsl.core.annotations.QueryEntity;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@QueryEntity
@Data
@Document
public class User {

    @Id
    private UUID id = UUID.randomUUID();

    private String firstName;

    private String lastName;

    private String address;

    private int index;

    private Instant createdAt;

    private Instant updatedAt;
}
