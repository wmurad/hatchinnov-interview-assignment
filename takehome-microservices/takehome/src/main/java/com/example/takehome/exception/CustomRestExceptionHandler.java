package com.example.takehome.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("handle MissingServletRequestParameterException");
	    String message = ex.getParameterName() + " parameter is required";
	    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	/*
	@ExceptionHandler({ ResponseStatusException.class })
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request){
		log.info("handle ResponseStatusException");
		ApiError apiError = new ApiError(ex.getStatus(), ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	*/
			
}
