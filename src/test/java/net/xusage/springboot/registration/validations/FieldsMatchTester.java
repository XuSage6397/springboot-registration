package net.xusage.springboot.registration.validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FieldsMatchTester {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void testFail() {

		User user = new User();
		user.setPassword("123456");
		user.setPasswordConfirm("111111");
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		assertThat(violations).isEmpty();
	}

	@Test
	public void testSuccess() {

		User user = new User();
		user.setPassword("111111");
		user.setPasswordConfirm("111111");
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		assertThat(violations).isEmpty();
	}

	@FieldsRelate
	public static class User {

		String password;
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPassword() {
			return password;
		}
		@FieldsMatch(relateField = "password")
		String passwordConfirm;
		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}
		public String getPasswordConfirm() {
			return passwordConfirm;
		}
	}
}
