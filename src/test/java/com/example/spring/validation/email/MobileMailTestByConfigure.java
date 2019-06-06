package com.example.spring.validation.email;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class MobileMailTestByConfigure extends SpringParameterized<MobileMailTestByConfigure.Form, String> {

	@Parameterized.Parameters
	public static List<Parameter> data() {

		return Arrays.asList(
				new Parameter("ok@gmail.com", 0),
				new Parameter("ok@sub.gmail.com", 0),
				new Parameter("ng@x.gmail.com", 1));
	}

	public MobileMailTestByConfigure(Parameter parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@MobileMail()
		final String value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}