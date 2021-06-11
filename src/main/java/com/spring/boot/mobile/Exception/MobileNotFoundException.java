package com.spring.boot.mobile.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "mbile not foud!!!")
public class MobileNotFoundException extends RuntimeException {
public MobileNotFoundException(String message) {
	super(message);
}
}
