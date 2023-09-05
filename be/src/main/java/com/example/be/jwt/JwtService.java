package com.example.be.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public interface JwtService {
    String extractUserName(String token);

    ResponseCookie generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String getJwtFromCookies(HttpServletRequest request);
}
