package com.spring.boot.mobile.controller;

import java.util.List;

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

import com.spring.boot.mobile.entity.Mobile;
import com.spring.boot.mobile.service.MobileServiceIml;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	MobileServiceIml mobileServiceIml;

	@GetMapping
	public List<Mobile> getAllMobiles() {

		return mobileServiceIml.getAllMobiles();
	}

	@GetMapping("/{mobile-id}")
	public Mobile getMobileById(@PathVariable("mobile-id") int mobileId) {
		return mobileServiceIml.getMobileById(mobileId);

	}

	@GetMapping("/byName")
	public List<Mobile> getMobileByName(@RequestParam("name") String name) {
		return mobileServiceIml.getMobileByName(name);

	}

	@GetMapping("/byPrice")
	public List<Mobile> getMobileByName(@RequestParam("price") long price) {
		return mobileServiceIml.getMobileByPrice(Double.valueOf(price));

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Mobile> saveMobile(@RequestBody Mobile mobile) {
		return mobileServiceIml.saveMobile(mobile);

	}

	@PutMapping("/{mobile-id}")
	public ResponseEntity<Mobile> updateMobile(@RequestBody Mobile mobile, @PathVariable("mobile-id") int mobileId) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(mobileServiceIml.updateMobile(mobile, mobileId));
	}

	@DeleteMapping("{mobile-id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void deleteMobile(@PathVariable("mobile-id") int id) {
		mobileServiceIml.deleteMobile(id);
	}

}
