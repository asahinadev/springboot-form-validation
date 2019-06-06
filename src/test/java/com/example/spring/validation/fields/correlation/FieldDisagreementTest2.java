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
public class FieldDisagreementTest2
		extends SpringParameterized<FieldDisagreementTest2.Form, FieldDisagreementTest2.ConfimeType<String>> {

	@Parameterized.Parameters
	public static List<ParameterBase<FieldDisagreementTest2.ConfimeType<String>>> data() {

		List<ParameterBase<FieldDisagreementTest2.ConfimeType<String>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				FieldDisagreementTest2.ConfimeType.<String>builder().field("a").fieldConfime("a").build(), 1));
		list.add(new ParameterBase<>(
				FieldDisagreementTest2.ConfimeType.<String>builder().field("a").fieldConfime("b").build(), 0));

		return list;
	}

	public FieldDisagreementTest2(ParameterBase<FieldDisagreementTest2.ConfimeType<String>> parameter) {

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

		@FieldDisagreement(field = "field", fieldConfime = "fieldConfime")
		final FieldDisagreementTest2.ConfimeType<String> value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
