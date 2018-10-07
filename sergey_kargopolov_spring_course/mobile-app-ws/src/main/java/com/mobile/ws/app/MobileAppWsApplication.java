package com.mobile.ws.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.mobile.ws.app.security.AppProperties;

@SpringBootApplication
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}
	//To encrypt password when application is started
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	//CENTRAL INTERFACE ALLOWING TO GET INFORMATION 
	//ABOUT APPLICATION'S CONFIGURATION
	@Bean
	public SpringApplicationContext springApplicationContext()
	{
		return new SpringApplicationContext();
		
	}
	@Bean(name="AppProperties")
	public AppProperties appProperties()
	{
		return new AppProperties();
	}
	
	}
