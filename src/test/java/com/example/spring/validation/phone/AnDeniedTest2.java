package com.example.spring.validation.phone;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class AnDeniedTest2
		extends SpringParameterized<AnDeniedTest2.Form, AnDeniedTest2.PhoneType> {

	@Parameterized.Parameters
	public static List<ParameterBase<AnDeniedTest2.PhoneType>> data() {

		List<ParameterBase<AnDeniedTest2.PhoneType>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				AnDeniedTest2.PhoneType.builder().tel1("0120").tel2("123").tel3("123").build(), 0));
		list.add(new ParameterBase<>(
				AnDeniedTest2.PhoneType.builder().tel1("0570").tel2("123").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				AnDeniedTest2.PhoneType.builder().tel1("0800").tel2("123").tel3("123").build(), 0));

		return list;
	}

	public AnDeniedTest2(ParameterBase<AnDeniedTest2.PhoneType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@An(denyTel1 = "0570")
		final AnDeniedTest2.PhoneType value;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class PhoneType {

		String tel1;

		String tel2;

		String tel3;

		@Override
		public String toString() {

			return String.format("%s%s%s", getTel1(), getTel2(), getTel3());
		}
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
