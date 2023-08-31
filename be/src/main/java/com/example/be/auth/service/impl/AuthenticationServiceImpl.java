package com.example.be.auth.service.impl;

import com.example.be.auth.entity.Role;
import com.example.be.auth.entity.User;
import com.example.be.auth.repository.RoleRepository;
import com.example.be.auth.repository.UserRepository;
import com.example.be.auth.request.SignInRequest;
import com.example.be.auth.request.SignUpRequest;
import com.example.be.auth.service.AuthenticationService;
import com.example.be.enums.ERole;
import com.example.be.exeption.ValidationException;
import com.example.be.jwt.JwtService;
import com.example.be.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> signUp(SignUpRequest signUpRequest) {
        validRequest(signUpRequest);
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        var user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt.toString()).body(signUpRequest);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt.toString()).body(signInRequest);
    }

    private void validRequest(SignUpRequest signUpRequest) {
        if (PasswordValidator.validPassword(signUpRequest.getPassword()))
            return;
        throw new ValidationException(messageSource.getMessage("response.message.validation.password", null, Locale.getDefault()));
    }
}
