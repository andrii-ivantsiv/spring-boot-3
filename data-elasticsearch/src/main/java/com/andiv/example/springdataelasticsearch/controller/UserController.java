package com.andiv.example.springdataelasticsearch.controller;

import com.andiv.example.springdataelasticsearch.entity.User;
import com.andiv.example.springdataelasticsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
public class UserController {

    //private final ElasticsearchOperations elasticsearchOperations;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody User user) {
        var query = new IndexQueryBuilder()
                .withId(user.getId().toString())
                .withObject(user)
                .build();
        return ResponseEntity.ok(
                //elasticsearchOperations.index(query, IndexCoordinates.of(User.INDEX_NAME))
                String.valueOf(userService.saveDocument(user).getId())
        );
    }

    @PutMapping
    public ResponseEntity<String> saveEntity(@RequestBody User user) {
        return ResponseEntity.ok(
                String.valueOf(userService.saveEntity(user).getId())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(
                //elasticsearchOperations.get(userId.toString(), User.class, IndexCoordinates.of(User.INDEX_NAME))
                userService.getById(userId)
        );
    }

    @GetMapping("/entities")
    public ResponseEntity<Iterable<User>> getAllEntities() {
        return ResponseEntity.ok(
                userService.getAllEntities()
        );
    }

    @GetMapping("/documents")
    public ResponseEntity<Iterable<User>> getAllDocuments() {
        return ResponseEntity.ok(
                userService.getAllDocuments()
        );
    }
}
