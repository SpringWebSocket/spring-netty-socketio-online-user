package com.phearun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phearun.entity.User;
import com.phearun.repository.UserRepository;
import com.phearun.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findUserByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("=>User not found!!!");
		
		return user;
	}

	@Override
	public boolean updateOnlineStatus(String clientId) {
		return userRepository.updateOnlineStatus(clientId);
	}
}
