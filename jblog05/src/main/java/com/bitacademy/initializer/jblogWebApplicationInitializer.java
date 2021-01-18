
package com.bitacademy.initializer; 


import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.bitacademy.jblog.config.AppConfig;
import com.bitacademy.jblog.config.WebConfig;

public class jblogWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context Config
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context Config
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// DispatcherServlet Mapping
		return new String[] { "/" };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// create DispatcherServlet
		DispatcherServlet diapatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
	
		// Exception Handler가 없으면 Exception을 던지게끔 설정
		diapatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		return diapatcherServlet;
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
	}
}