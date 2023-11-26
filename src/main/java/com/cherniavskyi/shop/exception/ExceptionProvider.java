package com.cherniavskyi.shop.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class ExceptionProvider {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) { //TODO change utils time
        Map<String, Object> errorDetails = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", response.getStatus(),
                "message", ex.getMessage(),
                "path", request.getServletPath()
        );

        log.warn(errorDetails.toString());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getWriter(), errorDetails);
    }

}
