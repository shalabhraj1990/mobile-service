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

import com.spring.boot.mobile.service.MobileServiceIml;
import com.spring.boot.mobile.utils.MobileUtilities;

import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.FilterDto;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.entity.Mobile;

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
	public Response<List<MobileDto>> getAllMobiles(FilterDto filterDto) {
		return mobileServiceIml.getAllMobilesSpecificationQuery(filterDto);

	}

	@GetMapping("/{mobile-id}")
	public Response<MobileDto> getMobileById(@PathVariable("mobile-id") int mobileId) {
		return mobileServiceIml.getMobileById(mobileId);

	}

	@GetMapping("/byName")
	public Response<List<MobileDto>> getMobileByName(@RequestParam("name") String name) {
		return mobileServiceIml.getMobileByName(name);

	}

	@GetMapping("/byPrice")
	public Response<List<MobileDto>> getMobileByName(@RequestParam("price") long price) {
		return mobileServiceIml.getMobileByPrice(Double.valueOf(price));

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Response<List<MobileDto>> saveMobile(@RequestBody @Valid SaveMobileDto mobile) {
		mobileUtilities.validateSaveMobileDto(mobile);
		return mobileServiceIml.saveMobile(mobile);

	}

	@PutMapping("/{mobile-id}")
	public ResponseEntity<Response<MobileDto>> updateMobile(@RequestBody Mobile mobile, @PathVariable("mobile-id") int mobileId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.updateMobile(mobile, mobileId));
	}

	@DeleteMapping("{mobile-id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Response<Void> deleteMobile(@PathVariable("mobile-id") int id) {
		return mobileServiceIml.deleteMobile(id);
	}

}
