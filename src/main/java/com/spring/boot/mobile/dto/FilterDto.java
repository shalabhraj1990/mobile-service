package com.spring.boot.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
	private String name;
	private Double price;
	private Status status;
	private LineOfBussiness lob;
}
