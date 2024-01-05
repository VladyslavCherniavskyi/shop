package com.cherniavskyi.shop.security.service.impl;

import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.exception.AuthorizationException;
import com.cherniavskyi.shop.security.JwtAuthenticationFilter;
import com.cherniavskyi.shop.security.service.JwtService;
import io.jsonwebtoken.*;
import io.vavr.control.Try;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.app.secretKey}")
    private String secretKey;

    @Value("${jwt.app.expirationMs}")
    private int expirationMs;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(expirationMs, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public boolean validateJwtToken(String token) {
        return Try.of(() -> {
                    var sub = extractUserName(token);
                    return Objects.equals(sub, extractUserName(token)) && !isTokenExpired(token);
                })
                .recover(MalformedJwtException.class, e -> handleError("Invalid JWT token: " + e.getMessage()))
                .recover(ExpiredJwtException.class, e -> handleError("JWT token is expired: " + e.getMessage()))
                .recover(UnsupportedJwtException.class, e -> handleError("JWT token is unsupported: " + e.getMessage()))
                .recover(IllegalArgumentException.class, e -> handleError("JWT claims string is empty: " + e.getMessage()))
                .getOrElse(() -> false);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        var header = request.getHeader(JwtAuthenticationFilter.AUTHORIZATION);
        return header.split(" ")[1].trim();
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean handleError(String errorMessage) {
        throw new AuthorizationException(errorMessage);
    }

}
