package com.mobile.ws.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mobile.ws.app.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// Core interface which loads user-specific data
	private final UserService userDetailsService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilter(getAuthenticationFilter())
				.addFilter(new AutorizationFilter(authenticationManager()))
				.sessionManagement()
				//Here we don't want http session(stateful) to be create
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	

}
