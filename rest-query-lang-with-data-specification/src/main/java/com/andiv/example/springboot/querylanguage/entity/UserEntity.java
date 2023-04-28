package com.andiv.example.springboot.querylanguage.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
