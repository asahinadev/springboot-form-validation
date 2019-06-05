package com.example.spring.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

	String tel1;

	String tel2;

	String tel3;

	public String toString() {

		return String.format("%s%s%s", getTel1(), getTel2(), getTel3());
	}
}
