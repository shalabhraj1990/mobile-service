package com.spring.boot.mobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mobile.Exception.MobileNotFoundException;
import com.spring.boot.mobile.entity.Mobile;
import com.spring.boot.mobile.repsitory.MobileRepository;

@Service
public class MobileServiceIml implements MobileService {
	@Autowired
	MobileRepository mobileRepositoy;

	List<Mobile> mobiles = new ArrayList<Mobile>();

	@Override
	public List<Mobile> getAllMobiles() {
		return mobileRepositoy.findAll();
	}

	@Override
	public Mobile getMobileById(int mobileId) {

		// Optional<Mobile> mobile = mobiles.stream().filter(x -> x.getId() ==
		// mobileId).findFirst();
		Optional<Mobile> mobile = mobileRepositoy.findById(mobileId);
		mobile.orElseThrow(() -> new MobileNotFoundException("mobile id not found !!!!"));
		return mobile.get();
	}

	@Override
	public List<Mobile> saveMobile(Mobile mobile) {
		mobile.setPublicationDate(LocalDate.now());
		mobileRepositoy.save(mobile);
//		if (!mobiles.contains(mobile)) {
//			mobiles.add(mobile);
//		}
		return getAllMobiles();
	}

	@Override
	public Mobile updateMobile(Mobile mobile, int mobileId) {
//		Mobile mobileFound = getMobileById(mobileId);
//		int index = mobiles.indexOf(mobileFound);
//		mobiles.set(index, mobile);
//		return mobile;
		getMobileById(mobileId);// if exist then no exception
		mobile.setId(mobileId);
		mobile.setPublicationDate(LocalDate.now());
		mobileRepositoy.save(mobile);
		return mobile;
	}

	@Override
	public void deleteMobile(int id) {
//		Mobile mobileFound = getMobileById(id);
//		int index = mobiles.indexOf(mobileFound);
//		mobiles.remove(index);
		mobileRepositoy.deleteById(id);

	}

	public List<Mobile> getMobileByName(String name) {
		// TODO Auto-generated method stub
		return mobileRepositoy.getByName(name);
	}

	@Override
	public List<Mobile> getMobileByPrice(Double price) {
		// TODO Auto-generated method stub
		return mobileRepositoy.getByPrice(price);
	}

//	@PostConstruct
//	public void init() {
//		Mobile mobile = new Mobile();
//		mobile.setId(1);
//		mobile.setName("samsung");
//		mobile.setPrice(5000.00);
//		mobile.setStatus("AVAILABLE");
//		mobile.setLob("online");
//		mobile.setCountryCode("KOR");
//		mobiles.add(mobile);
//
//		Mobile mobile1 = new Mobile();
//		mobile1.setId(2);
//		mobile1.setName("samsung");
//		mobile1.setPrice(5000.00);
//		mobile1.setStatus("AVAILABLE");
//		mobile1.setLob("retail");
//		mobile1.setCountryCode("KOR");
//		mobiles.add(mobile1);
//	}

}
