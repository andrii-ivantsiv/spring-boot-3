package com.andiv.example.springboot.querylanguage.dao;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchCriteria {
    @NonNull
    private final String key;
    @NonNull
    private final String operation;
    @NonNull
    private final Object value;

    public boolean isOrPredicate() {
        return operation.equalsIgnoreCase("or");
    }
}
