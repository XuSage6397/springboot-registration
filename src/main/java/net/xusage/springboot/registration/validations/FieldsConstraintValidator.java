package net.xusage.springboot.registration.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public interface FieldsConstraintValidator<A extends java.lang.annotation.Annotation, T> extends ConstraintValidator<A, T> {

	default boolean isValid(String fieldValue, ConstraintValidatorContext context) {
		return true;
	}

	boolean isValid(ConstraintValidatorContext constraintValidatorContext, T... values);
}
