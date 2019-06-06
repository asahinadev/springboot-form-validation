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
public class AnTest2
		extends SpringParameterized<AnTest2.Form, AnTest2.PhoneType> {

	@Parameterized.Parameters
	public static List<ParameterBase<AnTest2.PhoneType>> data() {

		List<ParameterBase<AnTest2.PhoneType>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("").tel2("").tel3("").build(), 0));

		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("").tel2("1").tel3("1").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1").tel2("").tel3("1").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1").tel2("1").tel3("").build(), 1));

		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("A").tel2("1").tel3("1").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1").tel2("A").tel3("1").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1").tel2("1").tel3("A").build(), 1));

		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("123").tel2("123").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("12345").tel2("123").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1234").tel2("12").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1234").tel2("1234").tel3("123").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1234").tel2("123").tel3("12").build(), 1));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1234").tel2("123").tel3("1234").build(), 1));

		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("1234").tel2("123").tel3("123").build(), 1));

		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("0120").tel2("123").tel3("123").build(), 0));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("0570").tel2("123").tel3("123").build(), 0));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("0800").tel2("123").tel3("123").build(), 0));
		list.add(new ParameterBase<>(
				AnTest2.PhoneType.builder().tel1("0000").tel2("123").tel3("123").build(), 1));

		return list;
	}

	public AnTest2(ParameterBase<AnTest2.PhoneType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@An
		final AnTest2.PhoneType value;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class PhoneType {

		String tel1;

		String tel2;

		String tel3;

		public String toString() {

			return String.format("%s%s%s", getTel1(), getTel2(), getTel3());
		}
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
