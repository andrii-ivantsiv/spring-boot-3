package org.example.mongodb.with.jpa.spec.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mongodb.with.jpa.spec.model.User;
import org.example.mongodb.with.jpa.spec.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @see org.example.mongodb.with.jpa.spec.api.UserController
 */
@Transactional
@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.17");

    @DynamicPropertySource
    static void mongoDBProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.data.mongodb.uri", () -> mongoDBContainer.getReplicaSetUrl("example_db"));
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUserTest() throws Exception {
        final UserRequest userRequest = new UserRequest("John");

        mockMvc.perform(put("/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(userRequest.name()));

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void findUsersTest() throws Exception {
        final String userName = "User2";
        userRepository.saveAll(
                List.of(
                        createUser("User1"),
                        createUser(userName),
                        createUser("User3")
                )
        );

        mockMvc.perform(get("/user?name={userName}", userName)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].name").value(userName));
    }

    private User createUser(String name) {
        final User user = new User();
        user.setName(name);
        return user;
    }
}