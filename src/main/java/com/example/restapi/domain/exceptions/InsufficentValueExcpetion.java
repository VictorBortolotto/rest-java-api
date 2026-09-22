package com.example.restapi.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficentValueExcpetion extends ApiException {
    public InsufficentValueExcpetion(String message) {
        super(message, HttpStatus.UNPROCESSABLE_CONTENT);
    }
}
