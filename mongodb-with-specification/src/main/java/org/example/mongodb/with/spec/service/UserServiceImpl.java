package org.example.mongodb.with.spec.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.mongodb.with.spec.api.SearchRequest;
import org.example.mongodb.with.spec.api.UserRequest;
import org.example.mongodb.with.spec.api.UserResponse;
import org.example.mongodb.with.spec.model.User;
import org.example.mongodb.with.spec.repository.UserQueryExample;
import org.example.mongodb.with.spec.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse save(UserRequest userRequest) {
        final User userToSave = new User();
        userToSave.setFirstName(userRequest.firstName());
        userToSave.setLastName(userRequest.lastName());
        userToSave.setAddress(userRequest.address());
        userToSave.setIndex(userRequest.index());
        return UserConverter.convertToResponse(userRepository.save(userToSave));
    }

    @Override
    public List<UserResponse> find(SearchRequest searchRequest) {
        return userRepository.findAll(UserQueryExample.of(searchRequest)).stream()
                .map(UserConverter::convertToResponse)
                .toList();
    }
}
