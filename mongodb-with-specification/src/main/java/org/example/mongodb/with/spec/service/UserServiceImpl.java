package org.example.mongodb.with.spec.service;

import lombok.RequiredArgsConstructor;
import org.example.mongodb.with.spec.api.SearchRequest;
import org.example.mongodb.with.spec.api.UserRequest;
import org.example.mongodb.with.spec.api.UserResponse;
import org.example.mongodb.with.spec.model.User;
import org.example.mongodb.with.spec.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse save(UserRequest userRequest) {
        final User userToSave = new User();
        userToSave.setName(userRequest.name());
        final User savedUser = userRepository.save(userToSave);
        return new UserResponse(savedUser.getId(), savedUser.getName());
    }

    @Override
    public List<UserResponse> find(SearchRequest searchRequest) {
        if (hasText(searchRequest.name())) {
            return userRepository.findByName(searchRequest.name()).stream()
                    .map(this::convert)
                    .toList();
        } else {
            return userRepository.findAll().stream()
                    .map(this::convert)
                    .toList();
        }
    }

    private UserResponse convert(User user) {
        return new UserResponse(user.getId(), user.getName());
    }
}
