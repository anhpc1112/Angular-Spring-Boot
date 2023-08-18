package com.example.be.auth.request;

import com.example.be.annotation.PasswordValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
//    @PasswordValidation(message = "{response.message.validation.password}")
    private String password;
}
