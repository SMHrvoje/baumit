package baumit.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidConstructionAddressValidator implements ConstraintValidator<ValidConstructionAddress, String> {

    private static final String addressRegexToMatch = "^[\\p{L}\\d\\s]+, \\d{4,7} [\\p{L}\\d\\s]+, [\\p{L}\\d\\s]+$";

    @Override
    public void initialize(ValidConstructionAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        //todo napraviti neku logiku

        return value.matches(addressRegexToMatch);
    }
}