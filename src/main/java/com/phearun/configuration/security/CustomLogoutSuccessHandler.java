package com.phearun.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.phearun.configuration.component.Property;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	@Autowired
	private Property prop;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		System.out.println("=>Logout Succes!");
		
		response.sendRedirect(prop.LOGOUT_SUCCESS_URL);
	}

}
