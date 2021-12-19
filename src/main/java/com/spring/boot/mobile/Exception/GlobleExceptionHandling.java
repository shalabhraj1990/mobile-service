package com.spring.boot.mobile.Exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import msk.spring.boot.common.dto.ErrorDetails;
import msk.spring.boot.common.dto.Response;

@ControllerAdvice
public class GlobleExceptionHandling {

	@ExceptionHandler(value = NullPointerException.class)
	ResponseEntity<Response<ErrorDetails>> handleNullPointerException(NullPointerException ex) {
		return getErrorDetails(ex, 1001);

	}

	@ExceptionHandler(value = Throwable.class)
	ResponseEntity<Response<ErrorDetails>> throwableException(Throwable ex) {
		return getErrorDetails(ex, 1000);
	}

	@ExceptionHandler(value = MobileNotFoundException.class)
	public ResponseEntity<Response<ErrorDetails>> handleMobileNotFoundException(MobileNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(1001, ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.<ErrorDetails>builder().errors(errorDetails).build());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Response<List<ErrorDetails>>> handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<ErrorDetails> validationErrors = bindingResult.getAllErrors().stream()
				.map(error -> ErrorDetails.builder().code(2001).message(error.getDefaultMessage()).build())
				.collect(Collectors.toList());
		return ResponseEntity.badRequest()
				.body(Response.<List<ErrorDetails>>builder().errors(validationErrors).build());
	}

	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<Response<List<ErrorDetails>>> handleInvalidInputException(InvalidInputException ex) {
		// return
		// ResponseEntity.badRequest().body(ErrorDetails.builder().code(ex.getCode()).message(ex.getMessage()).build());
		return ResponseEntity.badRequest().body(Response.<List<ErrorDetails>>builder().errors(ex.getErrors()).build());
	}

	private ResponseEntity<Response<ErrorDetails>> getErrorDetails(Throwable ex, int errorCode) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		ErrorDetails errorDetails = new ErrorDetails(5001, ex.getMessage(), stringWriter.toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.<ErrorDetails>builder().errors(errorDetails).build());
	}
}
