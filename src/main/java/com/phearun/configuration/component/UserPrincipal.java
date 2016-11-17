package com.phearun.configuration.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.phearun.entity.User;

@Component
public class UserPrincipal {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public User getPrincipal() {
		return (User) getAuthentication().getPrincipal();
	}
	
}
