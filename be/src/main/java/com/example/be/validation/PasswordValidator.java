package com.example.be.validation;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;

@RequiredArgsConstructor
public class PasswordValidator {
    //        implements ConstraintValidator<PasswordValidation, String> {
    private static final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

//    private final MessageSource messageSource;

    //    @Override
//    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
//        if (!password.matches(passwordRegex)) {
//            constraintValidatorContext.disableDefaultConstraintViolation(); // Disable default message
//            constraintValidatorContext.buildConstraintViolationWithTemplate(messageSource.getMessage("response.message.validation.password",
//                    null, Locale.getDefault())).addConstraintViolation();
//            return false;
//        }
//        return true;
//    }
    public static boolean validPassword(String password) {
        if (password.matches(passwordRegex))
            return true;
        return false;
    }
}
