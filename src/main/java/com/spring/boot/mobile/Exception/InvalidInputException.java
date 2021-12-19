package com.spring.boot.mobile.Exception;

import java.util.List;

import msk.spring.boot.common.dto.ErrorDetails;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
	// To handle the single enum error
//	private int code;
//
//	public InvalidInputException(String message, int errorCode) {
//		super(message);
//		this.code = errorCode;
//	}
//
//	public int getCode() {
//		return code;
//	}
	// To handle mutiple enum erro
	private List<ErrorDetails> errors;

	public InvalidInputException(String message, List<ErrorDetails> errors) {
		super(message);
		this.errors = errors;
	}

	public List<ErrorDetails> getErrors() {
		return errors;
	}
}
