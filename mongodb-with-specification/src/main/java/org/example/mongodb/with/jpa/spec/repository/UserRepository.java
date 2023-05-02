package org.example.mongodb.with.jpa.spec.repository;

import org.example.mongodb.with.jpa.spec.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    @Query("{ 'name' : ?0 }")
    List<User> findByName(String name);
}
