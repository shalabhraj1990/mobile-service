package com.spring.boot.mobile.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

import com.spring.boot.mobile.annotations.LogExecutionTime;
import com.spring.boot.mobile.service.MobileServiceIml;
import com.spring.boot.mobile.utils.MobileUtilities;
import com.spring.boot.mobile.utils.ThreadLocalUtil;

import lombok.extern.slf4j.Slf4j;
import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.FilterDto;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.entity.Mobile;

@RestController
@RequestMapping("/mobiles")
@Slf4j
public class MobileController {

	@Autowired
	MobileServiceIml mobileServiceIml;
	@Autowired
	MobileUtilities mobileUtilities;
	@Autowired
	ThreadLocalUtil threadLocalUtil;
//	@GetMapping
//	public List<Mobile> getAllMobiles(@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "price", required = false) Double price,
//			@RequestParam(name = "status", required = false) Status status,
//			@RequestParam(name = "lob", required = false) LineOfBussiness lob) {

	// return mobileServiceIml.getAllMobilesWithPalaceHolder(name, price, status,
	// lob);
	@LogExecutionTime
	@GetMapping
	public ResponseEntity<Response<List<MobileDto>>> getAllMobiles(FilterDto filterDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(mobileServiceIml.getAllMobilesSpecificationQuery(filterDto));

	}

	@LogExecutionTime
	@GetMapping("/{mobile-id}")
	public ResponseEntity<Response<MobileDto>> getMobileById(@PathVariable("mobile-id") int mobileId) {
		// localThread Demo
		threadLocalUtil.setThreadLocal(String.valueOf(mobileId));
		log.info("Thread name :" + Thread.currentThread().getName() + "mobileId :" + mobileId);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response<MobileDto> res = mobileServiceIml.getMobileById(mobileId);
		log.info("Thread name :" + Thread.currentThread().getName() + "mobileId :" + threadLocalUtil.getThreadLocal());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);

		// return mobileServiceIml.getMobileById(mobileId);
	}

	@LogExecutionTime
	@GetMapping("/byName")
	public ResponseEntity<Response<List<MobileDto>>> getMobileByName(@RequestParam("name") String name) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.getMobileByName(name));

	}

	@LogExecutionTime
	@GetMapping("/byPrice")
	public ResponseEntity<Response<List<MobileDto>>> getMobileByName(@RequestParam("price") long price) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(mobileServiceIml.getMobileByPrice(Double.valueOf(price)));

	}

	@LogExecutionTime
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response<List<MobileDto>>> saveMobile(@RequestBody @Valid SaveMobileDto mobile) {
		mobileUtilities.validateSaveMobileDto(mobile);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.saveMobile(mobile));

	}

	@LogExecutionTime
	@PutMapping("/{mobile-id}")
	public ResponseEntity<Response<MobileDto>> updateMobile(@RequestBody Mobile mobile,
			@PathVariable("mobile-id") int mobileId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.updateMobile(mobile, mobileId));
	}

	@LogExecutionTime
	@DeleteMapping("{mobile-id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<Response<Void>> deleteMobile(@PathVariable("mobile-id") int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.deleteMobile(id));
	}

}
