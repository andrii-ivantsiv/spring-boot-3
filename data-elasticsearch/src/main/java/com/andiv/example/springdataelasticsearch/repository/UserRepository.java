package com.andiv.example.springdataelasticsearch.repository;

import com.andiv.example.springdataelasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Integer> {

    User getById(Integer id);
}
