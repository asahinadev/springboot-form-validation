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
public class MobileTest2
		extends SpringParameterized<MobileTest2.Form, PhoneType> {

	@Parameterized.Parameters
	public static List<ParameterBase<PhoneType>> data() {

		List<ParameterBase<PhoneType>> list = new ArrayList<>();

		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("070").tel2("1234").tel3("1234").build(), 1));
		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("080").tel2("1234").tel3("1234").build(), 0));
		list.add(new ParameterBase<>(
				PhoneType.builder().tel1("090").tel2("1234").tel3("1234").build(), 0));

		return list;
	}

	public MobileTest2(ParameterBase<PhoneType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@Mobile(denyTel1 = "070")
		final PhoneType value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
