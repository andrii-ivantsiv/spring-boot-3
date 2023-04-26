package com.andiv.example.springdataelasticsearch.event;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Builder
@Getter
public class UserSavedEvent {
    @NonNull
    private String username;
}
