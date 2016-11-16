package com.phearun.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.phearun.configuration.component.Property;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	private Property prop;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		System.out.println("Authentication Failed: " + exception.getLocalizedMessage());
		
		response.sendRedirect(prop.LOGIN_ERROR_URL);
	}

}
