package baumit.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidConstructionAddressValidator implements ConstraintValidator<ValidConstructionAddress, String> {

    @Override
    public void initialize(ValidConstructionAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        //todo napraviti neku logiku
        return true;
    }
}