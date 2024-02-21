package net.xusage.springboot.registration.controller;

import net.xusage.springboot.registration.entity.UserPrincipal;
import net.xusage.springboot.registration.entity.UserSchema;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

	boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}

	UserPrincipal principal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (UserPrincipal) authentication.getPrincipal();
		}
		return null;
	}
}
