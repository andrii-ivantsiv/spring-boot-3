package com.andiv.example.springdataelasticsearch.service;

import com.andiv.example.springdataelasticsearch.entity.User;
import com.andiv.example.springdataelasticsearch.jpa.UserJpaRepository;
import com.andiv.example.springdataelasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional//rollback doesn't work for elasticsearch
    public User saveDocument(@NonNull User user) {
        var savedUser = userRepository.save(user.save());
        throwExceptionIfUsernameIsError(user);
        return savedUser;
    }

    @Transactional
    public User saveEntity(@NonNull User user) {
        var savedUser = userJpaRepository.save(user.save());
        throwExceptionIfUsernameIsError(user);
        return savedUser;
    }

    public Iterable<User> getAllEntities() {
        return userJpaRepository.findAll();
    }

    public Iterable<User> getAllDocuments() {
        return userRepository.findAll();
    }

    private void throwExceptionIfUsernameIsError(@NonNull User user) {
        if (Objects.equals(user.getUsername(), "error")) {
            throw new RuntimeException("Error!");
        }
    }

    public User getById(int userId) {
        return userRepository.getById(userId);
    }
}
