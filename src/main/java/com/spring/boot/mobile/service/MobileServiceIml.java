package com.spring.boot.mobile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.boot.mobile.Exception.MobileNotFoundException;
import com.spring.boot.mobile.repsitory.MobileRepository;

import msk.spring.boot.common.dto.Response;
import msk.spring.boot.common.mobile.dto.FilterDto;
import msk.spring.boot.common.mobile.dto.LineOfBussiness;
import msk.spring.boot.common.mobile.dto.MobileDto;
import msk.spring.boot.common.mobile.dto.SaveMobileDto;
import msk.spring.boot.common.mobile.dto.Status;
import msk.spring.boot.common.mobile.entity.Mobile;

@Service
public class MobileServiceIml implements MobileService {
	@Autowired
	MobileRepository mobileRepositoy;

	// List<Mobile> mobiles = new ArrayList<Mobile>();

	@Override
	public Response<List<MobileDto>> getAllMobilesWithPalaceHolderQuery(String name, Double price, Status status,
			LineOfBussiness lob) {
		List<MobileDto> mobiles = mobileRepositoy.getJpqlCustomFilterWithPlaceHolderQuery(name, price,
				Objects.nonNull(status) ? status.getValue() : null, Objects.nonNull(lob) ? lob.getValue() : null)
				.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	@Override
	public Response<List<MobileDto>> getAllMobilesWithNamedParameterQuery(String name, Double price, Status status,
			LineOfBussiness lob) {
		List<MobileDto> mobiles = mobileRepositoy.getJpqlCustomFilterWithPlaceHolderQuery(name, price,
				Objects.nonNull(status) ? status.getValue() : null, Objects.nonNull(lob) ? lob.getValue() : null)
				.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	@Override
	public Response<List<MobileDto>> getAllMobilesSpecificationQuery(FilterDto filterDto) {
//		Specification<Mobile> nameFilter = createFilter("name",filterDto.getName());
//		Specification<Mobile> priceFilter = createFilter("price",filterDto.getPrice());
//		Specification<Mobile> lobFilter = createFilter("lineOfBussiness",filterDto.getLob());
//		Specification<Mobile> statusFilter = createFilter("status",filterDto.getStatus());
//		
		Specification<Mobile> filter = createFilter("name", filterDto.getName())
				.and(createFilter("price", filterDto.getPrice()))
				.and(createFilter("lineOfBussiness", filterDto.getLob()))
				.and(createFilter("status", filterDto.getStatus()));

		List<MobileDto> mobiles = mobileRepositoy.findAll(filter).stream().map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	private Specification<Mobile> createFilter(String propertyName, Object value) {
		return new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (null == value)
					return null;
				if (value instanceof String && StringUtils.isEmpty((String) value))
					return null;
				if ("price".equalsIgnoreCase(propertyName))
					return criteriaBuilder.le(root.get(propertyName), (Number) value);

				return criteriaBuilder.equal(root.get(propertyName), value);
			}
		};
	}

	@Override
	public Response<MobileDto> getMobileById(int mobileId) {

		// Optional<Mobile> mobile = mobiles.stream().filter(x -> x.getId() ==
		// mobileId).findFirst();
		Optional<Mobile> mobile = mobileRepositoy.findById(mobileId);
		mobile.orElseThrow(() -> new MobileNotFoundException("mobile id not found !!!!"));
		MobileDto mobileRes = entityToDto(mobile.get());
		return Response.<MobileDto>builder().data(mobileRes).build();
	}

	@Override
	@Transactional
	public Response<List<MobileDto>> saveMobile(SaveMobileDto mobile) {
		Mobile mobEntity = Mobile.builder().name(mobile.getName()).countryCode(mobile.getCountryCode())
				.publicationDate(LocalDate.now()).status(Status.valueOf(mobile.getStatus()))
				.lineOfBussiness(LineOfBussiness.valueOf(mobile.getLineOfBussiness()))
				.accessoryType(StringUtils.isNotEmpty(mobile.getAccessoryType()) ? mobile.getAccessoryType() : "ALL")
				.price(mobile.getPrice()).build();

		mobileRepositoy.save(mobEntity);
//		if (!mobiles.contains(mobile)) {
//			mobiles.add(mobile);
//		}
		List<MobileDto> mobiles = mobileRepositoy.findAll().stream().map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	@Override
	public Response<MobileDto> updateMobile(Mobile mobile, int mobileId) {
//		Mobile mobileFound = getMobileById(mobileId);
//		int index = mobiles.indexOf(mobileFound);
//		mobiles.set(index, mobile);
//		return mobile;
		getMobileById(mobileId);// if exist then no exception
		mobile.setId(mobileId);
		mobile.setPublicationDate(LocalDate.now());
		mobileRepositoy.save(mobile);
		MobileDto mobileRes = entityToDto(mobile);
		return Response.<MobileDto>builder().data(mobileRes).build();
	}

	@Override
	public Response<Void> deleteMobile(int id) {
//		Mobile mobileFound = getMobileById(id);
//		int index = mobiles.indexOf(mobileFound);
//		mobiles.remove(index);
		mobileRepositoy.deleteById(id);
		return Response.<Void>builder().build();
	}

	public Response<List<MobileDto>> getMobileByName(String name) {
		// TODO Auto-generated method stub
		List<MobileDto> mobiles = mobileRepositoy.getByName(name).stream().map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	@Override
	public Response<List<MobileDto>> getMobileByPrice(Double price) {
		// TODO Auto-generated method stub
		List<MobileDto> mobiles = mobileRepositoy.getByPrice(price).stream().map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return Response.<List<MobileDto>>builder().data(mobiles).build();
	}

	private Mobile DtoToEntity(MobileDto mobileDto) {
		return Mobile.builder().id(mobileDto.getId()).accessoryType(mobileDto.getAccessoryType())
				.countryCode(mobileDto.getCountryCode())
				.lineOfBussiness(LineOfBussiness.valueOf(mobileDto.getLineOfBussiness())).name(mobileDto.getName())
				.price(mobileDto.getPrice()).publicationDate(mobileDto.getPublicationDate())
				.status(Status.valueOf(mobileDto.getStatus())).build();
	}

	private MobileDto entityToDto(Mobile mobile) {
		return MobileDto.builder().id(mobile.getId()).accessoryType(mobile.getAccessoryType())
				.countryCode(mobile.getCountryCode()).lineOfBussiness(String.valueOf(mobile.getLineOfBussiness()))
				.name(mobile.getName()).price(null != mobile.getPrice() ? mobile.getPrice() : 0)
				.publicationDate(mobile.getPublicationDate()).status(String.valueOf(mobile.getStatus())).build();
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
