package com.spring.boot.mobile.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.spring.boot.mobile.Exception.ErrorDetails;
import com.spring.boot.mobile.Exception.InvalidInputException;

import msk.spring.boot.common.mobile.dto.LineOfBussiness;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.dto.Status;

@Component
public class MobileUtilities {
	public void validateSaveMobileDto(SaveMobileDto saveMobileDto) {
	List<ErrorDetails> errors = new ArrayList<>();
	try {
		Status.valueOf(saveMobileDto.getStatus());
	} catch (IllegalArgumentException e) {
		errors.add(ErrorDetails.builder().code(2001).message("invalid Status value").build());
	}
	try {
		LineOfBussiness.valueOf(saveMobileDto.getLineOfBussiness());
	} catch (IllegalArgumentException e) {
		errors.add(ErrorDetails.builder().code(2001).message("invalid LineOfBussiness value").build());
	}

	if (!CollectionUtils.isEmpty(errors)) {
		throw new InvalidInputException("Request is invalid", errors);
	}
}
}
