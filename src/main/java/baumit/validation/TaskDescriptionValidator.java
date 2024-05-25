package baumit.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TaskDescriptionValidator implements ConstraintValidator<ValidTaskDescription, String> {

    @Override
    public void initialize(ValidTaskDescription constraintAnnotation) {
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