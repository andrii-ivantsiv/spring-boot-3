package com.andiv.example.springboot.querylanguage.controller;

import com.andiv.example.springboot.querylanguage.dao.SearchCriteria;
import com.andiv.example.springboot.querylanguage.dao.UserSpecificationsBuilder;
import com.andiv.example.springboot.querylanguage.entity.UserEntity;
import com.andiv.example.springboot.querylanguage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping("/users")
    public List<UserEntity> search(@RequestParam(value = "search", required = false) String search) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<UserEntity> spec = builder.build();
        return repository.findAll(spec);
    }
}
