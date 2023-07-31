package com.abdulchakam.loginservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IncorrectPasswordException extends RuntimeException{

    private final HttpStatus httpStatus;

    public IncorrectPasswordException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
