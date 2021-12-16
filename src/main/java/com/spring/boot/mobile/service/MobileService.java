package com.spring.boot.mobile.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.spring.boot.mobile.dto.FilterDto;
import com.spring.boot.mobile.dto.LineOfBussiness;
import com.spring.boot.mobile.dto.MobileDto;
import com.spring.boot.mobile.dto.SaveMobileDto;
import com.spring.boot.mobile.dto.Status;
import com.spring.boot.mobile.entity.Mobile;

public interface MobileService {
	public List<MobileDto> getAllMobilesWithPalaceHolderQuery(String name, Double price, Status status,
			LineOfBussiness lob);

	public List<MobileDto> getAllMobilesWithNamedParameterQuery(String name, Double price, Status status,
			LineOfBussiness lob);

	public List<MobileDto> getAllMobilesSpecificationQuery(FilterDto filterDto);

	public MobileDto getMobileById(int mobileId);

	public List<MobileDto> saveMobile(SaveMobileDto mobile);

	public MobileDto updateMobile(Mobile mobile, int mobileId);

	public void deleteMobile(@PathVariable("mobile-id") int id);

	public List<MobileDto> getMobileByName(String name);

	public List<MobileDto> getMobileByPrice(Double price);
}
