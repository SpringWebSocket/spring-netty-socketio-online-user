package com.phearun.configuration.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Property {
	
	@Value("${login.url}")
	public String LOGIN_URL;
	
	@Value("${login.url.success}")
	public String LOGIN_SUCCESS_URL;
	
	@Value("${login.url.admin}")
	public String ADMIN_SUCCESS_URL;
	
	@Value("${login.url.editor}")
	public String EDITOR_SUCCESS_URL;
	
	@Value("${login.url.user}")
	public String USER_SUCCESS_URL;
	
	@Value("${login.url.error}")
	public String LOGIN_ERROR_URL;
	
	@Value("${login.url.denied}")
	public String LOGIN_DENIED_URL;
	
	@Value("${login.request.target.url}")
	public String PREV_REQUEST_URL;
	
	@Value("${logout.url.success}")
	public String LOGOUT_SUCCESS_URL;
	
	@Value("${socket.io.host}")
	public String SOCKET_IO_HOST;
	
	@Value("${socket.io.port}")
	public Integer SOCKET_IO_PORT;
	
}
