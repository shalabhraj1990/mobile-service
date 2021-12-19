package com.spring.boot.mobile.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.FilterDto;
import msk.spring.boot.common.mobile.dto.LineOfBussiness;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.dto.Status;
import msk.spring.boot.common.mobile.entity.Mobile;



public interface MobileService {
	public Response<List<MobileDto>> getAllMobilesWithPalaceHolderQuery(String name, Double price, Status status,
			LineOfBussiness lob);

	public Response<List<MobileDto>> getAllMobilesWithNamedParameterQuery(String name, Double price, Status status,
			LineOfBussiness lob);

	public Response<List<MobileDto>> getAllMobilesSpecificationQuery(FilterDto filterDto);

	public Response<MobileDto> getMobileById(int mobileId);

	public Response<List<MobileDto>> saveMobile(SaveMobileDto mobile);

	public Response<MobileDto> updateMobile(Mobile mobile, int mobileId);

	public Response<Void>  deleteMobile(@PathVariable("mobile-id") int id);

	public Response<List<MobileDto>> getMobileByName(String name);

	public Response<List<MobileDto>> getMobileByPrice(Double price);
}
