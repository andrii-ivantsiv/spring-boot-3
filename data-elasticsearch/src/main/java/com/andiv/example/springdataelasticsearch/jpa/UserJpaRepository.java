package com.andiv.example.springdataelasticsearch.jpa;

import com.andiv.example.springdataelasticsearch.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<User, Integer> {
}
