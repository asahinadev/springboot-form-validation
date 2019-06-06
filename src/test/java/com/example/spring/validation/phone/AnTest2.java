package com.example.spring.validation.phone;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;
import com.example.spring.form.PhoneType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class AnTest2
		extends SpringParameterized<AnTest2.Form, PhoneType> {

	@Parameterized.Parameters
	public static List<ParameterBase<PhoneType>> data() {

		List<ParameterBase<PhoneType>> list = new ArrayList<>();

		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("0120").tel2("123").tel3("123").build(), 0));
		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("0570").tel2("123").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("0800").tel2("123").tel3("123").build(), 0));

		return list;
	}

	public AnTest2(ParameterBase<PhoneType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@An(denyTel1 = "0570")
		final PhoneType value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
