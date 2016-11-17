package com.phearun.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
	public static void main(String[] args) {
		String password = "123";
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		
	}
}
