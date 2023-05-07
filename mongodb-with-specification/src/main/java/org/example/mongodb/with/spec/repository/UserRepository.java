package org.example.mongodb.with.spec.repository;

import java.util.UUID;
import org.example.mongodb.with.spec.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, UUID> /*, QuerydslPredicateExecutor<User>*/ {}
