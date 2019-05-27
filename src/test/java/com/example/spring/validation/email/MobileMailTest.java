package com.example.spring.validation.email;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.example.spring.SpringParameterized;

import lombok.Builder;
import lombok.Data;

public class MobileMailTest extends SpringParameterized {

	@Parameterized.Parameters
	public static List<Parameter> data() {
		return Arrays.asList(
				new Parameter("a@gmail.com", 0),
				new Parameter("b@x.gmail.com", 1),
				new Parameter("c@yahoo.co.jp", 1));
	}

	public MobileMailTest(Parameter parameter) {
		super(parameter);
	}

	@Data
	@Builder
	public static class Form {
		@MobileMail
		String value;
	}

	@Autowired
	Validator validator;

	@Test
	public void test() {
		Form form = Form.builder().value(parameter.value).build();

		result = new BeanPropertyBindingResult(form, "form");
		validator.validate(form, result);
		assertThat(result.getErrorCount(), is(parameter.errorCount));

	}

}
