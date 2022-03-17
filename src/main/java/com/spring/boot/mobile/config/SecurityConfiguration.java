//package com.spring.boot.mobile.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	UserDetailsService userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//		// mobile-user
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//
//		http.authorizeRequests().anyRequest()
//				// .authenticated()
//				.hasRole("MOBILE_USER").and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().httpBasic();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//
//		return new BCryptPasswordEncoder();
//	}
//}
