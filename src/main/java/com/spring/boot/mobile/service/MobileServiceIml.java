package com.spring.boot.mobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.boot.mobile.Exception.MobileNotFoundException;
import com.spring.boot.mobile.dto.FilterDto;
import com.spring.boot.mobile.dto.LineOfBussiness;
import com.spring.boot.mobile.dto.Status;
import com.spring.boot.mobile.entity.Mobile;
import com.spring.boot.mobile.repsitory.MobileRepository;

@Service
public class MobileServiceIml implements MobileService {
	@Autowired
	MobileRepository mobileRepositoy;

	List<Mobile> mobiles = new ArrayList<Mobile>();

	@Override
	public List<Mobile> getAllMobilesWithPalaceHolderQuery(String name, Double price, Status status,
			LineOfBussiness lob) {
		return mobileRepositoy.getJpqlCustomFilterWithPlaceHolderQuery(name, price,
				Objects.nonNull(status) ? status.getValue() : null, Objects.nonNull(lob) ? lob.getValue() : null);
	}

	@Override
	public List<Mobile> getAllMobilesWithNamedParameterQuery(String name, Double price, Status status,
			LineOfBussiness lob) {
		return mobileRepositoy.getJpqlCustomFilterWithPlaceHolderQuery(name, price,
				Objects.nonNull(status) ? status.getValue() : null, Objects.nonNull(lob) ? lob.getValue() : null);
	}

	@Override
	public List<Mobile> getAllMobilesSpecificationQuery(FilterDto filterDto) {
//		Specification<Mobile> nameFilter = createFilter("name",filterDto.getName());
//		Specification<Mobile> priceFilter = createFilter("price",filterDto.getPrice());
//		Specification<Mobile> lobFilter = createFilter("lineOfBussiness",filterDto.getLob());
//		Specification<Mobile> statusFilter = createFilter("status",filterDto.getStatus());
//		
		Specification<Mobile> filter = createFilter("name", filterDto.getName())
				.and(createFilter("price", filterDto.getPrice()))
				.and(createFilter("lineOfBussiness", filterDto.getLob()))
				.and(createFilter("status", filterDto.getStatus()));

		return mobileRepositoy.findAll(filter);
	}

	private Specification<Mobile> createFilter(String propertyName, Object value) {
		return new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (null == value)
					return null;
				if (value instanceof String && StringUtils.isEmpty((String) value))
					return null;
				if("price".equalsIgnoreCase(propertyName))
					return criteriaBuilder.le(root.get(propertyName),(Number) value);

				return criteriaBuilder.equal(root.get(propertyName), value);
			}
		};
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
		return mobileRepositoy.findAll();
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
