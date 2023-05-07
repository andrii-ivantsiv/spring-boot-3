package org.example.mongodb.with.spec.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.example.mongodb.with.spec.model.User;
import org.example.mongodb.with.spec.repository.UserRepository;
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

/**
 * @see UserController
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
        final UserRequest userRequest = new UserRequest("John", "Dou", "Str", 12345);

        mockMvc.perform(put("/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value(userRequest.firstName()));

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void findUsersTest() throws Exception {
        userRepository.saveAll(List.of(
                createUser("UserF1", "UserL1", "Address1", 12345),
                createUser("UserF2", "UserL2", "Address2", 12354),
                createUser("UserF3", "UserL3", "Address3", 54321)));

        mockMvc.perform(get("/user?firstName=UserF2&index=12354").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[*].id").isNotEmpty())
                .andExpect(jsonPath("$[*].firstName").value("UserF2"))
                .andExpect(jsonPath("$[*].lastName").value("UserL2"))
                .andExpect(jsonPath("$[*].address").value("Address2"))
                .andExpect(jsonPath("$[*].index").value(12354));
    }

    private User createUser(String firstName, String lastName, String address, Integer index) {
        final User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setIndex(index);
        return user;
    }
}
