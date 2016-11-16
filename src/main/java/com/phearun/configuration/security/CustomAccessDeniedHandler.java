package com.phearun.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.phearun.configuration.component.Property;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	@Autowired
	private Property prop;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		System.out.println("Access Denied: " + accessDeniedException.getLocalizedMessage());
		
		response.sendRedirect(prop.LOGIN_DENIED_URL);
	}

}
