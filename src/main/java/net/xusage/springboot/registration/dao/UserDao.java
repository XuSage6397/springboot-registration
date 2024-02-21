package net.xusage.springboot.registration.dao;

import net.xusage.springboot.registration.entity.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserSchema, Integer> {

	UserSchema findByEmail(String email);

}
