package org.example.mongodb.with.spec.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mongodb.with.spec.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        log.debug("Create new user {}", request);
        return userService.save(request);
    }

    @GetMapping
    public List<UserResponse> findUsers(@RequestParam(name = "name", required = false) String name) {
        final SearchRequest searchRequest = SearchRequest.builder().name(name).build();
        log.debug("Find user {}", searchRequest);
        return userService.find(searchRequest);
    }
}
