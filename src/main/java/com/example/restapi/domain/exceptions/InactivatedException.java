package com.example.restapi.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InactivatedException extends ApiException {
    public InactivatedException(String message) {
        super(message, HttpStatus.GONE);
    }
}
