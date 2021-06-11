package com.spring.boot.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.spring.boot.mobile.Exception.MobileNotFoundException;
import com.spring.boot.mobile.data.Mobile;

@Service 
public class MobileServiceIml implements MobileService {
	List<Mobile> mobiles = new ArrayList<Mobile>();

	@Override
	public List<Mobile> getAllMobiles() {
		return mobiles;
	}

	@Override
	public Mobile getMobileById(int mobileId) {
		Optional<Mobile> mobile = mobiles.stream().filter(x -> x.getId() == mobileId).findFirst();
		mobile.orElseThrow(() -> new MobileNotFoundException("mobile id not found !!!!"));
		return mobile.get();
	}

	@Override
	public List<Mobile> saveMobile(Mobile mobile) {
		if (!mobiles.contains(mobile)) {
			mobiles.add(mobile);
		}
		return mobiles;
	}

	@Override
	public Mobile updateMobile(Mobile mobile, int mobileId) {
		Mobile mobileFound = getMobileById(mobileId);
		int index = mobiles.indexOf(mobileFound);
		mobiles.set(index, mobile);
		return mobile;
	}

	@Override
	public void deleteMobile(int id) {
		Mobile mobileFound = getMobileById(id);
		int index = mobiles.indexOf(mobileFound);
		mobiles.remove(index);

	}

	@PostConstruct
	public void init() {
		Mobile mobile = new Mobile();
		mobile.setId(1);
		mobile.setName("samsung");
		mobile.setPrice(5000.00);
		mobile.setStatus("AVAILABLE");
		mobile.setLob("online");
		mobile.setCountryCode("KOR");
		mobiles.add(mobile);

		Mobile mobile1 = new Mobile();
		mobile1.setId(2);
		mobile1.setName("samsung");
		mobile1.setPrice(5000.00);
		mobile1.setStatus("AVAILABLE");
		mobile1.setLob("retail");
		mobile1.setCountryCode("KOR");
		mobiles.add(mobile1);
	}

}
