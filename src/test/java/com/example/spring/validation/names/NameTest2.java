package com.example.spring.validation.names;

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
public class NameTest2
		extends SpringParameterized<NameTest2.Form, NameTest2.NameType> {

	@Parameterized.Parameters
	public static List<ParameterBase<NameTest2.NameType>> data() {

		List<ParameterBase<NameTest2.NameType>> list = new ArrayList<>();

		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("").lastName("").build(), 0));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("").lastName("1").build(), 1));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("1").lastName("").build(), 1));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("1").lastName("1").build(), 0));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("1234567890").lastName("1234567890").build(), 0));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("12345678901").lastName("1234567890").build(), 1));
		list.add(new ParameterBase<>(
				NameTest2.NameType.builder().firstName("1234567890").lastName("12345678901").build(), 1));

		return list;
	}

	public NameTest2(ParameterBase<NameType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@Name
		final NameType value;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NameType {

		String firstName;

		String lastName;

	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
