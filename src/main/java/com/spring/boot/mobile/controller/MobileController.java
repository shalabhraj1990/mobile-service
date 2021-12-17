package com.spring.boot.mobile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mobile.dto.FilterDto;
import com.spring.boot.mobile.dto.MobileDto;
import com.spring.boot.mobile.dto.SaveMobileDto;
import com.spring.boot.mobile.entity.Mobile;
import com.spring.boot.mobile.service.MobileServiceIml;
import com.spring.boot.mobile.utils.MobileUtilities;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	MobileServiceIml mobileServiceIml;
	@Autowired
	MobileUtilities mobileUtilities;
//	@GetMapping
//	public List<Mobile> getAllMobiles(@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "price", required = false) Double price,
//			@RequestParam(name = "status", required = false) Status status,
//			@RequestParam(name = "lob", required = false) LineOfBussiness lob) {

	// return mobileServiceIml.getAllMobilesWithPalaceHolder(name, price, status,
	// lob);
	@GetMapping
	public List<MobileDto> getAllMobiles(FilterDto filterDto) {
		return mobileServiceIml.getAllMobilesSpecificationQuery(filterDto);

	}

	@GetMapping("/{mobile-id}")
	public MobileDto getMobileById(@PathVariable("mobile-id") int mobileId) {
		return mobileServiceIml.getMobileById(mobileId);

	}

	@GetMapping("/byName")
	public List<MobileDto> getMobileByName(@RequestParam("name") String name) {
		return mobileServiceIml.getMobileByName(name);

	}

	@GetMapping("/byPrice")
	public List<MobileDto> getMobileByName(@RequestParam("price") long price) {
		return mobileServiceIml.getMobileByPrice(Double.valueOf(price));

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<MobileDto> saveMobile(@RequestBody @Valid SaveMobileDto mobile) {
		mobileUtilities.validateSaveMobileDto(mobile);
		return mobileServiceIml.saveMobile(mobile);

	}

	@PutMapping("/{mobile-id}")
	public ResponseEntity<MobileDto> updateMobile(@RequestBody Mobile mobile, @PathVariable("mobile-id") int mobileId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.updateMobile(mobile, mobileId));
	}

	@DeleteMapping("{mobile-id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteMobile(@PathVariable("mobile-id") int id) {
		mobileServiceIml.deleteMobile(id);
	}

}
