package com.spring.boot.mobile.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.boot.mobile.Exception.MobileNotFoundException;
import com.spring.boot.mobile.repsitory.MobileRepository;

import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.LineOfBussiness;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.dto.Status;
import msk.spring.boot.common.mobile.entity.Mobile;

@SpringBootTest(classes = MobileServiceIml.class)
public class MobileServiceImlTest {
	@MockBean
	MobileRepository mobileRepositoy;
	@Autowired
	MobileService mobileService;

	@Test
	public void testGetMobileById() {
		when(mobileRepositoy.findById(1)).thenReturn(mockOptionalMobile());
		Response<MobileDto> reponse = mobileService.getMobileById(1);
		Assertions.assertNotNull(reponse);
		Assertions.assertNotNull(reponse.getData());
		Assertions.assertEquals("Motorola", reponse.getData().getName());
		Assertions.assertEquals(Status.AVAILABLE.toString(), reponse.getData().getStatus());
	}

	@Test
	public void testGetMobileByIdWhenDbReturnNoData() {
		when(mobileRepositoy.findById(1)).thenReturn(null);
		Assertions.assertThrows(NullPointerException.class, () -> mobileService.getMobileById(1));
	}

	@Test
	public void testGetMobileByIdWhenDbReturnEmptyOptional() {
		when(mobileRepositoy.findById(1)).thenReturn(Optional.empty());
		Exception ex = Assertions.assertThrows(MobileNotFoundException.class, () -> mobileService.getMobileById(1));
		Assertions.assertEquals("mobile id not found !!!!", ex.getMessage());
	}
	
	@Test
	public void testSaveMobileVoid() {
		mobileService.saveMobile(mockSaveMobileDto());
		verify(mobileRepositoy).save(mockOptionalMobile().get());
	}

	private SaveMobileDto mockSaveMobileDto() {
		// TODO Auto-generated method stub
		return SaveMobileDto.builder().name("Motorola").accessoryType("ALL").countryCode("USA").price(10000.0).status(String.valueOf(Status.AVAILABLE)  )
				.lineOfBussiness(String.valueOf(LineOfBussiness.ONLINE)).build();
	}

	private Optional<Mobile> mockOptionalMobile() {
		return Optional
				.of(Mobile.builder().name("Motorola").countryCode("USA").price(10000.0).status(Status.AVAILABLE)
						.lineOfBussiness(LineOfBussiness.ONLINE).publicationDate(LocalDate.now()).build());
	}
}
