package com.andiv.example.springdataelasticsearch.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorEntity> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.internalServerError()
                .body(ErrorEntity.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error(ex.getClass().getSimpleName())
                        .message(ex.getMessage())
                        .build());
    }

    @Getter
    @Builder
    public static class ErrorEntity {
        private int status;
        private String error;
        private String message;
    }
}
