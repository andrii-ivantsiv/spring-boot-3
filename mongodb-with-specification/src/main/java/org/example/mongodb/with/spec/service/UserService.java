package org.example.mongodb.with.spec.service;

import org.example.mongodb.with.spec.api.SearchRequest;
import org.example.mongodb.with.spec.api.UserRequest;
import org.example.mongodb.with.spec.api.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);

    List<UserResponse> find(SearchRequest searchRequest);
}
