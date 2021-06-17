package com.spring.boot.mobile.repsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.boot.mobile.data.LineOfBussiness;
import com.spring.boot.mobile.data.Status;
import com.spring.boot.mobile.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer> {
	List<Mobile> getByName(String name);

	List<Mobile> getByPrice(Double price);

//plceholder
	@Query(value = "select m from Mobile m where (?1 is null or m.name = ?1)" + "and (?2 is null or m.price = ?2)"
			+ "and (?3 is null or m.status = ?3)" + "and (?4 is null or m.lineOfBussiness = ?4)")
	List<Mobile> getJpqlCustomFilterWithPlaceHolderQuery(String name, Double price, Integer status, Integer lob);

	// named parameter
	@Query(value = "select m from Mobile m where (:name is null or m.name = :name)"
			+ "and (:price is null or m.price = :price)" + "and (:status is null or m.status = :status)"
			+ "and (:lob is null or m.lineOfBussiness = :lob)")
	List<Mobile> getJpqlCustomFilterWithNamedParameterQuery(String name, Double price, Status status, LineOfBussiness lob);
	
	//native Query
	@Query(value = "select * from Mobile m where (:name is null or m.name = :name)"
			+ "and (:price is null or m.price = :price)" + "and (:status is null or m.status = :status)"
			+ "and (:lob is null or m.line_of_business = :lob)",nativeQuery = true)
	List<Mobile> getJpqlCustomFilterNativeQuery(String name, Double price, Status status, LineOfBussiness lob);
}
