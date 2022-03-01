package com.spring.boot.mobile.repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import msk.spring.boot.common.mobile.entity.MobileUsersEntity;

public interface MobileUsersRepository extends JpaRepository<MobileUsersEntity, Integer> {
	Optional<MobileUsersEntity> findByUsername(String name);
}
