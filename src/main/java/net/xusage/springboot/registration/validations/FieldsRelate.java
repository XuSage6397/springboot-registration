package net.xusage.springboot.registration.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsRelateValidator.class)
public @interface FieldsRelate {

	String message() default "Fields do not match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	/**
	 * The prefix of the annotation, which will help validator execute the relate checking logic.
	 * @return
	 */
	Class<?>[] annotations() default {};
}