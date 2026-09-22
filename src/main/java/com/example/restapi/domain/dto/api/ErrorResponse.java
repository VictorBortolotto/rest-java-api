package com.example.restapi.domain.dto.api;

import java.util.Map;

public record ErrorResponse(String message, Map<String, String> errors) { }
