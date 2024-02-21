package net.xusage.springboot.registration.validations;

import jakarta.validation.ConstraintValidatorContext;

public class FieldsMatchValidator implements FieldsConstraintValidator<FieldsMatch, Object> {

	@Override
	public void initialize(final FieldsMatch constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {
		return true;
	}

	@Override
	public boolean isValid(ConstraintValidatorContext context, Object... values) {
		return values[0] == null && values[1] == null || values[0] != null && values[0].equals(values[1]);

	}
}

