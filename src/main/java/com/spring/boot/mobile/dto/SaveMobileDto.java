//package com.spring.boot.mobile.dto;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//
//import org.springframework.util.CollectionUtils;
//
//import com.spring.boot.mobile.Exception.ErrorDetails;
//import com.spring.boot.mobile.Exception.InvalidInputException;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class SaveMobileDto {
//
//	private String accessoryType; 
//	@NotBlank(message = "{NotBlank.SaveMobileDto.countryCode}")
//	private String countryCode;
//	@NotBlank(message = "{NotBlank.SaveMobileDto.lineOfBussiness}")
//	private String lineOfBussiness;
//	@NotBlank(message = "{NotBlank.SaveMobileDto.name}")
//	private String name;
//	@Min(value = 1, message = "{Min.SaveMobileDto.price}")
//	private double price;
//	@NotBlank(message = "{NotBlank.SaveMobileDto.status}")
//	private String status;
//
////This method can be moved to util class
////	public void validateSaveMobileDto() {
////		List<ErrorDetails> errors = new ArrayList<>();
////		try {
////			Status.valueOf(this.status);
////		} catch (IllegalArgumentException e) {
////			errors.add(ErrorDetails.builder().code(2001).message("invalid Status value").build());
////		}
////		try {
////			LineOfBussiness.valueOf(this.lineOfBussiness);
////		} catch (IllegalArgumentException e) {
////			errors.add(ErrorDetails.builder().code(2001).message("invalid LineOfBussiness value").build());
////		}
////
////		if (!CollectionUtils.isEmpty(errors)) {
////			throw new InvalidInputException("Request is invalid", errors);
////		}
////	}
//
//}
