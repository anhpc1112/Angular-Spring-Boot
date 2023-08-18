package com.example.be.auth.service.impl;

import com.example.be.auth.entity.User;
import com.example.be.auth.repository.UserRepository;
import com.example.be.auth.request.SignInRequest;
import com.example.be.auth.request.SignUpRequest;
import com.example.be.auth.response.JwtAuthenticationResponse;
import com.example.be.auth.service.AuthenticationService;
import com.example.be.enums.Role;
import com.example.be.exeption.ValidationException;
import com.example.be.jwt.JwtService;
import com.example.be.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        validRequest(signUpRequest);
        var user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private boolean validRequest(SignUpRequest signUpRequest) {
        if (PasswordValidator.validPassword(signUpRequest.getPassword()))
            return true;
        throw new ValidationException(messageSource.getMessage("response.message.validation.password", null, Locale.getDefault()));
    }
}
