package com.example.be.validation;

import com.example.be.annotation.PasswordValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    private final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password.matches(passwordRegex)) return true;
        return false;
    }
}
