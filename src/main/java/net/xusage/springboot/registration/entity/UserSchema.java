package net.xusage.springboot.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import net.xusage.springboot.registration.validations.FieldUnique;
import net.xusage.springboot.registration.validations.FieldsMatch;
import net.xusage.springboot.registration.validations.FieldsRelate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@FieldsRelate
public class UserSchema extends BaseSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank(message = "Last Name must not be blank", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdate.class})
	@Size(min = 2, message = "Last Name must be at least 3 characters long", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdate.class})
	String lastName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotBlank(message = "Last Name must not be blank", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdate.class})
	@Size(min = 2, message = "Last Name must be at least 3 characters long", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdate.class})
	String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotBlank(message = "Email must not be blank", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdateEmail.class})
	@Email(message = "Please provide a valid email address", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdateEmail.class})
	@FieldUnique(entity = UserSchema.class, fieldName = "email", message = "Email existed!", groups = {ValidateGroups.OnCreate.class})
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message = "Password must not be blank", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdatePassword.class})
	@Size(min = 6, message = "Password must be at least 6 characters long", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdatePassword.class})
	String password;

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Transient
	@NotBlank(message = "Password must not be blank", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdatePassword.class})
	@Size(min = 6, message = "Password must be at least 6 characters long", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdatePassword.class})
	@FieldsMatch(relateField = "password", message = "Must be same as password", groups = {ValidateGroups.OnCreate.class, ValidateGroups.OnUpdatePassword.class})
	String passwordConfirm;

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	@Transient
	@NotBlank(message = "Password must not be blank", groups = {ValidateGroups.OnUpdatePassword.class})
	@Size(min = 6, message = "Password must be at least 6 characters long", groups = {ValidateGroups.OnUpdatePassword.class})
	String passwordOld;

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public interface ValidateGroups extends net.xusage.springboot.registration.validations.ValidateGroups {
		interface OnUpdatePassword { }

		interface OnUpdateEmail { }
	}
}
