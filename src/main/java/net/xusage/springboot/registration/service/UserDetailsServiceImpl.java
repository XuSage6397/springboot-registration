package net.xusage.springboot.registration.service;

import net.xusage.springboot.registration.dao.UserDao;
import net.xusage.springboot.registration.entity.UserPrincipal;
import net.xusage.springboot.registration.entity.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Custom spring security authorization from user and by email and password.
 * This service used in SecurityConfig.
 *
 * @see net.xusage.springboot.registration.config.SecurityConfig
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserSchema user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new UserPrincipal(
				user.getId(),
				user.getEmail(),
				user.getPassword(),
				Collections.emptyList()
		);
	}
}