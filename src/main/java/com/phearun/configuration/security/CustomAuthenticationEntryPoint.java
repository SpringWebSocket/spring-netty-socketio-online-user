package com.phearun.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.phearun.configuration.component.Property;

/**
 * - if the user not authorized to access the requested url, the override method of this class will work
 * - and then send the user to login page
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	@Autowired
	private Property prop;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		
		//TODO: set session for remember user previous request url, see: CustomAuthenticationSuccessHandler.class		
		request.getSession().setAttribute(prop.PREV_REQUEST_URL, request.getRequestURL().toString());
		
		response.sendRedirect(prop.LOGIN_URL);
	}
}
