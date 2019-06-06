package com.example.spring.validation.fields.correlation;

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
public class FieldConfimeTest2
		extends SpringParameterized<FieldConfimeTest2.Form, FieldConfimeTest2.ConfimeType<String>> {

	@Parameterized.Parameters
	public static List<ParameterBase<FieldConfimeTest2.ConfimeType<String>>> data() {

		List<ParameterBase<FieldConfimeTest2.ConfimeType<String>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				FieldConfimeTest2.ConfimeType.<String>builder().field("a").fieldConfime("a").build(), 0));
		list.add(new ParameterBase<>(
				FieldConfimeTest2.ConfimeType.<String>builder().field("a").fieldConfime("b").build(), 1));

		return list;
	}

	public FieldConfimeTest2(ParameterBase<FieldConfimeTest2.ConfimeType<String>> parameter) {

		super(parameter);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ConfimeType<E> {

		E field;

		E fieldConfime;

	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@FieldConfime(field = "field", fieldConfime = "fieldConfime")
		final FieldConfimeTest2.ConfimeType<String> value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
