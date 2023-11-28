package com.cherniavskyi.shop.exception;

import com.cherniavskyi.shop.util.TimeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class ExceptionProvider {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map<String, Object> errorDetails = Map.of(
                "timestamp", TimeUtils.formatter(new Date()),
                "status", response.getStatus(),
                "message", ex.getMessage(),
                "path", request.getServletPath()
        );

        log.warn(errorDetails.toString());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getWriter(), errorDetails);
    }

}
