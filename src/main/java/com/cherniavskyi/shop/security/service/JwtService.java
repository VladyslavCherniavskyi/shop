package com.cherniavskyi.shop.security.service;

import com.cherniavskyi.shop.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {

    String generateToken(User user);

    boolean validateJwtToken(String token);

    String getToken(HttpServletRequest httpServletRequest);

    String extractUserName(String token);

}
