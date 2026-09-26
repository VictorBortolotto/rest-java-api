package com.example.restapi.exception;

import com.example.restapi.domain.dto.api.ErrorResponse;
import com.example.restapi.domain.exceptions.ConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    void shouldHandleApiException() {

        ConflictException exception =
                new ConflictException("Costumer already exists.");

        ResponseEntity<ErrorResponse> response =
                handler.handleApiException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        assertNotNull(response.getBody());

        assertEquals(
                "Costumer already exists.",
                response.getBody().message()
        );

        assertNull(response.getBody().errors());
    }

    @Test
    void shouldHandleValidationException() {

        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);

        FieldError nameError =
                new FieldError(
                        "costumer",
                        "name",
                        "Name is required."
                );

        FieldError emailError =
                new FieldError(
                        "costumer",
                        "email",
                        "Email is invalid."
                );

        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        when(bindingResult.getFieldErrors())
                .thenReturn(List.of(nameError, emailError));

        ResponseEntity<ErrorResponse> response =
                handler.handleValidationException(exception);

        assertEquals(
                HttpStatus.BAD_REQUEST,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                "Validation error",
                response.getBody().message()
        );

        assertNotNull(response.getBody().errors());

        assertEquals(
                "Name is required.",
                response.getBody().errors().get("name")
        );

        assertEquals(
                "Email is invalid.",
                response.getBody().errors().get("email")
        );
    }
}
