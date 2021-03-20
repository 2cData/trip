package com.toseedata.wetripout.trip.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EscapeCharacterValidator implements ConstraintValidator<EscapeCharacterConstraint, String> {

    @Override
    public void initialize(EscapeCharacterConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        // handle optional fields
        if (field == null || field.isEmpty() || field.isBlank()) return true;

        // any alphanumeric plus spaces
        String pattern = "^[a-zA-Z0-9 ]+$";
        return field.matches(pattern);

    }
}
