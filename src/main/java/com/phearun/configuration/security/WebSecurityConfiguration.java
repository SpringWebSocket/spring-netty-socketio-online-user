package com.phearun.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.phearun.configuration.component.Property;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private Property prop;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.formLogin()
				.loginPage(prop.LOGIN_URL)
				//.usernameParameter("")
				//.passwordParameter("")
				.successHandler(successHandler)
				//.successForwardUrl("")
				//.defaultSuccessUrl("")
				//.defaultSuccessUrl("", true)
				.failureHandler(authenticationFailureHandler)
				//.failureForwardUrl("")
				//.failureUrl("")
				.permitAll()
			.and()
				.logout()
				//.logoutUrl("")
				//.logoutSuccessUrl("")
				.logoutSuccessHandler(logoutSuccessHandler)
				
				//.deleteCookies("JSESSIONID")
				//.invalidateHttpSession(true)
			.and()
				.exceptionHandling()
				//.accessDeniedPage("")
				.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint);
		
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")	
			.antMatchers("/editor/**").hasAnyRole("EDITOR", "ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER", "EDITOR", "ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		String password = "123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}
}
