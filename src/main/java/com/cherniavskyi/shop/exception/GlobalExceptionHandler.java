package com.cherniavskyi.shop.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<? extends Exception>, HttpStatus> exceptions = Map.of(
            EntityNotFoundException.class, HttpStatus.NOT_FOUND,
            AccessDeniedException.class, HttpStatus.FORBIDDEN
    );

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleException(Exception exception) {
        var status = exceptions.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(status)
                .body(errorResponse(status, exception.getMessage()));
    }

    private Map<String, Object> errorResponse(HttpStatus status, String message) { //TODO create utils time
        return Map.of(
                "timestamp", OffsetDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }

}
