package com.spring.boot.mobile.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import msk.spring.boot.common.mobile.dto.FilterDto;
import msk.spring.boot.common.mobile.dto.LineOfBussiness;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.dto.Status;
import msk.spring.boot.common.mobile.entity.Mobile;



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
