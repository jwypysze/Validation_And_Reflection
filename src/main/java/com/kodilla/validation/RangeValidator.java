package com.kodilla.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {

    String annotationMin;
    String annotationMax;

    @Override
    public void initialize(Range constraintAnnotation) {
        annotationMin = constraintAnnotation.min();
        annotationMax = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int min = Integer.parseInt(annotationMin);
        int max = Integer.parseInt(annotationMax);
        return (value >= min) && (value <= max);
    }
}
