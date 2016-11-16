package com.phearun.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.phearun.configuration.component.Property;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private Property prop;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String redirectUrl = (String) request.getSession().getAttribute(prop.PREV_REQUEST_URL);

		// TODO:
		if (redirectUrl != null) {
			response.sendRedirect(redirectUrl); //filter chain
			// System.out.println("after response, code below still work...");
			return;
		}

		// TODO: determine redirect url
		for (GrantedAuthority auth : authentication.getAuthorities()) {
			System.out.println(auth.getAuthority());
			if (auth.getAuthority().equals("ROLE_ADMIN")) {
				redirectUrl = prop.ADMIN_SUCCESS_URL;
			} else if (auth.getAuthority().equals("ROLE_EDITOR")) {
				redirectUrl = prop.EDITOR_SUCCESS_URL;
			} else if (auth.getAuthority().equals("ROLE_USER")) {
				redirectUrl = prop.USER_SUCCESS_URL;
			} else {
				redirectUrl = prop.USER_SUCCESS_URL;
			}
		}
		response.sendRedirect(redirectUrl);
	}
}

// cannot commit response:
// http://stackoverflow.com/questions/2123514/java-lang-illegalstateexception-cannot-forward-sendredirect-after-response-ha