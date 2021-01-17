package com.bitacademy.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.bitacademy.config.web.FileUploadConfig;
import com.bitacademy.config.web.MessageSourceConfig;
import com.bitacademy.config.web.MvcConfig;
import com.bitacademy.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.bitacademy.jblog.controller", "com.bitacademy.jblog.exception"})
@Import({SecurityConfig.class, MvcConfig.class, MessageSourceConfig.class})
public class WebConfig {

}


// TODO FileUploadConfig.class 기능 추가 