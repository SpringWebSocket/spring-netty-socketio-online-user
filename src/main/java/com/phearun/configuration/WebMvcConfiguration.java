package com.phearun.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.phearun.configuration.component.Property;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	private Property prop;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/").setViewName(prop.LOGIN_URL);
		
		registry.addViewController("/editor").setViewName("/editor/dashboard");
		registry.addViewController("/editor/dashboard").setViewName("/editor/dashboard");
		
		registry.addViewController("/user").setViewName("/user/dashboard");
		registry.addViewController("/user/dashboard").setViewName("/user/dashboard");

		registry.addViewController("/admin").setViewName("/admin/dashboard");
		registry.addViewController("/admin/dashboard").setViewName("/admin/dashboard");
		
		
		//TODO: Authentication
		registry.addViewController(prop.LOGIN_URL).setViewName(prop.LOGIN_URL);
		
		//TODO: Error url handler
		registry.addViewController(prop.LOGIN_DENIED_URL).setViewName(prop.LOGIN_DENIED_URL);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
}
