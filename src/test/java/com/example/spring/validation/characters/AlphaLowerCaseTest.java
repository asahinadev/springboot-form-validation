package com.example.spring.validation.characters;

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

public class AlphaLowerCaseTest extends SpringParameterized {

	@Parameterized.Parameters
	public static List<Parameter> data() {
		return Arrays.asList(
				new Parameter("abcdefghijklmnopqrstuvwxyz", 0),
				new Parameter("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1),
				new Parameter("0123456789", 1),
				new Parameter("あ", 1),
				new Parameter("ア", 1),
				new Parameter("-", 1));
	}

	public AlphaLowerCaseTest(Parameter parameter) {
		super(parameter);
	}

	@Data
	@Builder
	public static class Form {
		@LowerCase
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
