package com.mobile.ws.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AutorizationFilter extends BasicAuthenticationFilter{

	public AutorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		
		//Here we are checking if header begin with "Bearer" or is null
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX))
		{
			//Route request to another filter
			chain.doFilter(req, res);
			//Nothing to return except 403 error
			return;
		}
		
		//If header is containing Bearer, we can route the request for authentication
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);
		
		//Here we are holding all security informations associated to the request
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		//Here we are routing request to another filter(Authentication filter)
		chain.doFilter(req, res);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req)
	{
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(token != null)
		{
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
			
			String userData =  Jwts
					.parser()
					//Its has to be the same token send by server, so it must be correct!
					.setSigningKey(SecurityConstants.getTokenSecret())
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			if(userData != null) {
				
				return new UsernamePasswordAuthenticationToken(userData,null,new ArrayList<>());
				
			}
			
			return null;
		}
		
		return null;
	}
	

}
