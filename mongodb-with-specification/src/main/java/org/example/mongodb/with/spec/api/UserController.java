package org.example.mongodb.with.spec.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mongodb.with.spec.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<UserResponse> findUsers(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "index", required = false) Integer index) {
        final SearchRequest searchRequest = SearchRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .index(index)
                .build();
        log.debug("Find user {}", searchRequest);
        return userService.find(searchRequest);
    }
}
