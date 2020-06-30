package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 *  TECH: @ControllerAdvice disable to use @RestControllerAdvice 
 */

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), 
															"From Methode ArgumentNotValidException in G.E.H", 
														     ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), 
				"From Methode RequestMethodNotSupportedException in G.E.H - Method Not Allowed", 
			     ex.getMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	/*
	 * TECH : Handle UserNameNotFoundException
	 */
	@ExceptionHandler(UserNameNotFoundException.class)
	protected ResponseEntity<Object> handleUserNameNotFoundException(
			UserNameNotFoundException ex, WebRequest request) {
        
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), 
			     ex.getMessage(),
			     request.getDescription(false));
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);		
	}
	
	//ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(
			ConstraintViolationException ex, WebRequest request) {
		
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), 
			     ex.getMessage(),
			     request.getDescription(false));
			
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);	
	}
		
}
