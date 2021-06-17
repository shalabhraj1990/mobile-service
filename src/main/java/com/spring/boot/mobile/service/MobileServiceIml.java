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
import com.spring.boot.mobile.data.LineOfBussiness;
import com.spring.boot.mobile.data.Status;
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
	public List<Mobile> getAllMobilesSpecificationQuery(String name, Double price, Status status, LineOfBussiness lob) {
		Specification<Mobile> nameFilter = new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				if (org.apache.commons.lang3.StringUtils.isEmpty(name))
					return null;
				return criteriaBuilder.equal(root.get("name"), name);
			}
		};

		Specification<Mobile> priceFilter = new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				if (Objects.isNull(price))
					return null;
				return criteriaBuilder.equal(root.get("price"), price);
			}
		};
		Specification<Mobile> lobFilter = new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				if (null == lob)
					return null;
				return criteriaBuilder.equal(root.get("lineOfBussiness"), lob);

			}
		};

		Specification<Mobile> statusFilter = new Specification<Mobile>() {

			@Override
			public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				if (null == status)
					return null;
				return criteriaBuilder.equal(root.get("status"), status);
			}
		};
		return mobileRepositoy.findAll(nameFilter.and(priceFilter).and(lobFilter).and(statusFilter));
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
