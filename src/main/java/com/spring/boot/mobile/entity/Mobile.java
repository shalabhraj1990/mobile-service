//package com.spring.boot.mobile.entity;
//
//import java.time.LocalDate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import com.spring.boot.mobile.dto.LineOfBussiness;
//import com.spring.boot.mobile.dto.Status;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//
//@Entity
//public class Mobile {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	@Column(name = "accessory_type")
//	private String accessoryType;
//	@Column(name = "country_code")
//	private String countryCode;
//	@Column(name = "line_of_business")
//	private LineOfBussiness lineOfBussiness;
//	private String name;
//	private Double price;
//	@Column(name = "publicatoin_date")
//	private LocalDate publicationDate;
//	private Status status;//my sql this column will set to integer 
//}
