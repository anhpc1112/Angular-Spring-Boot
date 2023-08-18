package com.example.be.auth.request;

import com.example.be.annotation.PasswordValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String email;
//    @PasswordValidation(message = "{response.message.validation.password}")
    private String password;
}
