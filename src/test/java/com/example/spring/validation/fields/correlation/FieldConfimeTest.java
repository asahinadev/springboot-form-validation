package com.example.spring.validation.fields.correlation;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;
import com.example.spring.form.ConfimeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class FieldConfimeTest extends SpringParameterized<FieldConfimeTest.Form, ConfimeType<String>> {

	@Parameterized.Parameters
	public static List<ParameterBase<ConfimeType<String>>> data() {

		List<ParameterBase<ConfimeType<String>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(
				ConfimeType.<String>builder().field("a").fieldConfime("a").build(), 0));
		list.add(new ParameterBase<>(
				ConfimeType.<String>builder().field("a").fieldConfime("b").build(), 1));

		return list;
	}

	public FieldConfimeTest(ParameterBase<ConfimeType<String>> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@FieldConfime(field = "field", fieldConfime = "fieldConfime")
		final ConfimeType<String> value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
