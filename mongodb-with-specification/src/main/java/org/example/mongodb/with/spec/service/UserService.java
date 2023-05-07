package org.example.mongodb.with.spec.service;

import java.util.List;
import org.example.mongodb.with.spec.api.SearchRequest;
import org.example.mongodb.with.spec.api.UserRequest;
import org.example.mongodb.with.spec.api.UserResponse;

public interface UserService {
    UserResponse save(UserRequest userRequest);

    List<UserResponse> find(SearchRequest searchRequest);
}
