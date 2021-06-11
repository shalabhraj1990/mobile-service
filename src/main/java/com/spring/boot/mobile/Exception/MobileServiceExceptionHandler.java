package com.spring.boot.mobile.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MobileServiceExceptionHandler {
	@ExceptionHandler(value = MobileNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(1001, ex.getMessage(),null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
}
