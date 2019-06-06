package com.example.spring.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneType {

	String tel1;

	String tel2;

	String tel3;

	@Override
	public String toString() {

		return String.format("%s%s%s", getTel1(), getTel2(), getTel3());
	}
}
