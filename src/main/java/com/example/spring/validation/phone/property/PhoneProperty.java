package com.example.spring.validation.phone.property;

public interface PhoneProperty {

	public String getTel1();

	public String getTel2();

	public String getTel3();

	default String getTelEntire() {
		return String.format("%s%s%s", getTel1(), getTel2(), getTel3());
	}
}
