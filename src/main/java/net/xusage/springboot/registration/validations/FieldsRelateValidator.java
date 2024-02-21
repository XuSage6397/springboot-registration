package net.xusage.springboot.registration.validations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldsRelateValidator implements ConstraintValidator<FieldsRelate, Object> {

	List<Class<? extends Annotation>> annotationClasses = Arrays.asList(
			FieldsMatch.class
	);
	@Override
	public void initialize(FieldsRelate constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapperImpl wrapper = new BeanWrapperImpl(value);
		boolean valid = true;

		for (Field field : value.getClass().getDeclaredFields()) {
			// check all the annotation
			for (Class<? extends Annotation> annotationClass: annotationClasses) {
				Annotation annotation = field.getAnnotation(annotationClass);
				if(annotation == null) {
					continue;
				}
				Object fieldValue = wrapper.getPropertyValue(field.getName());
				Object relateFieldValue = wrapper.getPropertyValue(getAnnotationValue(annotation, "relateField"));

				for (Class<?> validatorClass: getValidatorClasses(annotationClass)) {
					if (FieldsConstraintValidator.class.isAssignableFrom(validatorClass)) {
						try {
							FieldsConstraintValidator fieldsConstraintValidator = (FieldsConstraintValidator)validatorClass.newInstance();
							fieldsConstraintValidator.initialize(annotation);
							valid = fieldsConstraintValidator.isValid(context, fieldValue, relateFieldValue);
							if (!valid) {
								context.disableDefaultConstraintViolation();
								context.buildConstraintViolationWithTemplate(getAnnotationValue(annotation, "message"))
										.addPropertyNode(field.getName())
										.addConstraintViolation();
								break;
							}
						}
						catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException(validatorClass.getName(), e);
						}
					}
				}
			}
		}

		return valid;
	}

	public Class<?>[] getValidatorClasses(Class<?> constraintAnnotationClass) {
		Constraint constraintAnnotation = constraintAnnotationClass.getAnnotation(Constraint.class);
		if (constraintAnnotation != null) {
			return constraintAnnotation.validatedBy();
		}
		return new Class<?>[0];
	}

	public <T> T getAnnotationValue(Annotation annotation, String attributeName) {
		try {
			Class<?> annotationType = annotation.annotationType();
			Method method = annotationType.getDeclaredMethod(attributeName);
			return (T)method.invoke(annotation);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
