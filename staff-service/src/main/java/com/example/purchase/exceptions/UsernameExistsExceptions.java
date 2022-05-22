package com.example.purchase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameExistsExceptions extends RuntimeException {

    public UsernameExistsExceptions(String message) {
        super(message);
    }

}
