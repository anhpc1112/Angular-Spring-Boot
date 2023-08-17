package com.example.be.auth;

import com.example.be.auth.request.SignInRequest;
import com.example.be.auth.request.SignUpRequest;
import com.example.be.auth.response.JwtAuthenticationResponse;
import com.example.be.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Validated SignInRequest request){
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

}
