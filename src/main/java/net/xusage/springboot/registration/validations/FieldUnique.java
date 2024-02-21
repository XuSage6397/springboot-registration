package net.xusage.springboot.registration.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldUniqueValidator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldUnique {
	String message() default "This field must be unique";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> entity();

	String fieldName();
}
