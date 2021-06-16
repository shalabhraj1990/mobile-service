package com.spring.boot.mobile.repsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.mobile.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Integer> {
	List<Mobile> getByName(String name);
	List<Mobile> getByPrice(Double price);
}
