package com.mobile.ws.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	
	//Interface representing the environment in which the current application is running
	@Autowired
	private Environment env;
	
	//Here we are reading SECRET TOKEN from application property
	public String getTokenSecret()
	{
		return env.getProperty("tokenSecret");
	}

}
