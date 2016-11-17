package com.phearun.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.phearun.entity.User;

public interface UserService extends UserDetailsService{
	
	List<User> findAllOnlineUsers();
	
	boolean updateOnlineAndClientId(int userId, String clientId);
	
	boolean updateOfflineByClientId(String clientId);
	
}
