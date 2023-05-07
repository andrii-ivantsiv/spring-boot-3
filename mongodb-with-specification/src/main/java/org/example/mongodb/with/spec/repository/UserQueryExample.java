package org.example.mongodb.with.spec.repository;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

import lombok.experimental.UtilityClass;
import org.example.mongodb.with.spec.api.SearchRequest;
import org.example.mongodb.with.spec.model.User;
import org.springframework.data.domain.Example;

@UtilityClass
public class UserQueryExample {
    public static Example<User> of(SearchRequest searchRequest) {
        final User user = new User();
        user.setId(null);
        if (nonNull(searchRequest)) {
            if (hasText(searchRequest.firstName())) {
                user.setFirstName(searchRequest.firstName());
            }
            if (hasText(searchRequest.lastName())) {
                user.setLastName(searchRequest.lastName());
            }
            if (hasText(searchRequest.address())) {
                user.setAddress(searchRequest.address());
            }
            if (nonNull(searchRequest.index())) {
                user.setIndex(searchRequest.index());
            }
        }
        return Example.of(user);
    }
}
