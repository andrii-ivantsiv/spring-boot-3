package org.example.mongodb.with.spec.service;

import lombok.experimental.UtilityClass;
import org.example.mongodb.with.spec.api.UserResponse;
import org.example.mongodb.with.spec.model.User;

@UtilityClass
public class UserConverter {
    public static UserResponse convertToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .index(user.getIndex())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
