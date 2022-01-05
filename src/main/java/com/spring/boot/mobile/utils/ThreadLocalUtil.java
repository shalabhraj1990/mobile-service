package com.spring.boot.mobile.utils;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUtil {
	public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static String getThreadLocal() {
		return threadLocal.get();
	}

	public static void setThreadLocal(String val) {
		threadLocal.set(val);
	}

}
