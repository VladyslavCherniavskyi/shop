package com.cherniavskyi.shop.exception;

import com.cherniavskyi.shop.util.TimeUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<? extends Exception>, HttpStatus> exceptions = Map.of(
            EntityNotFoundException.class, HttpStatus.NOT_FOUND,
            AccessDeniedException.class, HttpStatus.FORBIDDEN,
            MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST,
            ConstraintViolationException.class, HttpStatus.BAD_REQUEST,
            DataIntegrityViolationException.class, HttpStatus.BAD_REQUEST,
            ValidationException.class, HttpStatus.BAD_REQUEST,
            BadCredentialsException.class, HttpStatus.BAD_REQUEST,
            MultipartConvertException.class, HttpStatus.BAD_REQUEST,
            FileDeletionException.class, HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleException(Exception exception) {
        var status = exceptions.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(status)
                .body(errorResponse(status, exception.getMessage()));
    }

    private Map<String, Object> errorResponse(HttpStatus status, String message) {
        return Map.of(
                "timestamp", TimeUtils.formatter(new Date()),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }

}
