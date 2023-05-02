package org.example.mongodb.with.jpa.spec.service;

import org.example.mongodb.with.jpa.spec.api.SearchRequest;
import org.example.mongodb.with.jpa.spec.api.UserRequest;
import org.example.mongodb.with.jpa.spec.api.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);

    List<UserResponse> find(SearchRequest searchRequest);
}
