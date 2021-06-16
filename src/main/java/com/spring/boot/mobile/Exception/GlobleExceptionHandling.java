package com.spring.boot.mobile.Exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandling {

	@ExceptionHandler(value = NullPointerException.class)
	ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException ex){
		return getErrorDetails(ex,1001);
		
	}
	
	@ExceptionHandler(value = Throwable.class)
	ResponseEntity<ErrorDetails> throwableException(Throwable ex){
		return getErrorDetails(ex,1000);
	}
	
	@ExceptionHandler(value = MobileNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(1001, ex.getMessage(),null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
	
	private ResponseEntity<ErrorDetails> getErrorDetails(Throwable ex,int errorCode) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		ErrorDetails errorDetails = new ErrorDetails(5001, ex.getMessage(),stringWriter.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}
}
