package com.example.be.auth.service;

import com.example.be.auth.request.SignInRequest;
import com.example.be.auth.request.SignUpRequest;
import com.example.be.auth.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
