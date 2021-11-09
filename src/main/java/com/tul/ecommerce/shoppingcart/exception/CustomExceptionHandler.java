package com.tul.ecommerce.shoppingcart.exception;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	

	//excepción validación javax validation 
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handlerValidationException(ConstraintViolationException ex, WebRequest request) {
		String message = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();
		ExceptionResponse errorResponse = new ExceptionResponse(new Date(),"Error Validation Constraint", message);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	//excepciones para productException
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handlerAllException(Exception ex, WebRequest request){
		ExceptionResponse customExceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false) );
		return new ResponseEntity<Object>(customExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
