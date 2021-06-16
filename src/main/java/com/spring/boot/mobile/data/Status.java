package com.spring.boot.mobile.data;

public enum Status {
	AVAILABLE(0), NOTAVAILABLE(1);

	private int value;

	private Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
	
}
