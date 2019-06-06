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
public class MobileDeniedTest2
		extends SpringParameterized<MobileDeniedTest2.Form, MobileDeniedTest2.PhoneType> {

	@Parameterized.Parameters
	public static List<ParameterBase<MobileDeniedTest2.PhoneType>> data() {

		List<ParameterBase<MobileDeniedTest2.PhoneType>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				MobileDeniedTest2.PhoneType.builder().tel1("070").tel2("1234").tel3("1234").build(), 1));
		list.add(new ParameterBase<>(
				MobileDeniedTest2.PhoneType.builder().tel1("080").tel2("1234").tel3("1234").build(), 0));
		list.add(new ParameterBase<>(
				MobileDeniedTest2.PhoneType.builder().tel1("090").tel2("1234").tel3("1234").build(), 0));

		return list;
	}

	public MobileDeniedTest2(ParameterBase<MobileDeniedTest2.PhoneType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@Mobile(denyTel1 = "070")
		final MobileDeniedTest2.PhoneType value;
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
