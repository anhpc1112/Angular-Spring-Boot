package com.example.be.jwt;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    ResponseCookie generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
