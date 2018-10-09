package com.mobile.ws.app.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.mobile.ws.app.ui.model.response.CustomedErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	
	/*
	 * @A controller advice allows you to use exactly the same exception handling 
	 * techniques but apply them across the whole application, not just to an individual 
	 * controller. You can think of them as an annotation driven interceptor.
	 * */
	
	//WebRequest gives access to general request metadata in controllers
	@ExceptionHandler(value = {UserServiceExceptions.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceExceptions ex, WebRequest req)
	{
		CustomedErrorMessage errorMessage = new CustomedErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

		
}
