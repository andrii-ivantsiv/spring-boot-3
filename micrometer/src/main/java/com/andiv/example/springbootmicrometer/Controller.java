package com.andiv.example.springbootmicrometer;

import io.micrometer.core.annotation.Timed;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Timed(value = "controller.hello")
@Slf4j
@RestController
public class Controller {

    @GetMapping("/hello")
    @SneakyThrows
    public ResponseEntity<String> hello() {
        var random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("Error");
        }
        Thread.sleep(random.nextInt(10000));
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/bye")
    @SneakyThrows
    public ResponseEntity<String> bye() {
        var random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("Error");
        }
        Thread.sleep(random.nextInt(20000));
        return ResponseEntity.ok("Bye!");
    }
}
