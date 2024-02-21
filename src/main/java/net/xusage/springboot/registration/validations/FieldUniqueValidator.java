package net.xusage.springboot.registration.validations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class FieldUniqueValidator implements ConstraintValidator<FieldUnique, String> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<?> entityClass;
	private String fieldName;

	@Override
	public void initialize(FieldUnique constraintAnnotation) {
		this.entityClass = constraintAnnotation.entity();
		this.fieldName = constraintAnnotation.fieldName();
	}

	@Override
	public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
		if (fieldValue == null) {
			return true;
		}
		String queryString = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :fieldValue", entityClass.getName(), fieldName);
		long count = 0;
		try {
			TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
			query.setParameter("fieldValue", fieldValue);
			count = query.getSingleResult();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return count == 0;
	}
}
