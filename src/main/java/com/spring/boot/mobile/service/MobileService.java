package com.spring.boot.mobile.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.spring.boot.mobile.data.Mobile;

public interface MobileService {
	public List<Mobile> getAllMobiles();

	public Mobile getMobileById(int mobileId);

	public List<Mobile> saveMobile(Mobile mobile);

	public Mobile updateMobile(Mobile mobile,int mobileId);

	public void deleteMobile(@PathVariable("mobile-id") int id);
}
