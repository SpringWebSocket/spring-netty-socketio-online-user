package com.phearun.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	
	boolean updateOnlineStatus(String clientId);
	
}
