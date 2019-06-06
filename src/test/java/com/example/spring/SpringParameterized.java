package com.example.spring;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SpringParameterized<E> {

	@ClassRule
	public static final SpringClassRule RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule rule = new SpringMethodRule();

	@Autowired
	Validator validator;

	protected BindingResult result;

	@RequiredArgsConstructor
	public static class Parameter {

		final public String value;

		final public int errorCount;

		@Override
		public String toString() {

			return ToStringBuilder
					.reflectionToString(this, ToStringStyle.JSON_STYLE);
		}
	}

	final protected Parameter parameter;

	public SpringParameterized(Parameter parameter) {

		this.parameter = parameter;
	}

	@Before
	public void setUp() throws Exception {

		log.info("parameter {}", parameter);
	}

	@Test
	public void test() {

		E form = createForm();

		result = new BeanPropertyBindingResult(form, "form");
		validator.validate(form, result);

		assertThat(result.getErrorCount(),
				equalTo(parameter.errorCount));

	}

	protected abstract E createForm();

	@After
	public void tearDown() throws Exception {

		if (result != null) {
			log.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
			result.getFieldErrors().stream().forEach(item -> {
				log.info("{} : {}", item.getField(), item.getDefaultMessage());
			});
		}
		log.info("------------------------------------------------------------");
		Thread.sleep(10L);
	}

}
