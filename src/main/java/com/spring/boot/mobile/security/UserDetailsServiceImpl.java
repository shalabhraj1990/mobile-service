//package com.spring.boot.mobile.security;
//
//import java.util.Optional;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.spring.boot.mobile.repsitory.MobileUsersRepository;
//
//import msk.spring.boot.common.mobile.entity.MobileUsersEntity;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	MobileUsersRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if (StringUtils.isEmpty(username)) {
//			throw new UsernameNotFoundException("User Name Should Not Be Empty !!!");
//		}
//		Optional<MobileUsersEntity> dbUser = repository.findByUsername(username);
//
//		dbUser.orElseThrow(() -> new UsernameNotFoundException("User Name Not Found!!!"));
//
//		return User.builder().username(dbUser.get().getUsername()).password(dbUser.get().getPassword())
//				.disabled(!dbUser.get().getActive()).roles(dbUser.get().getRoles()).build();
//	}
//
//}
