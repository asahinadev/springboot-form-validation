package com.example.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public abstract class SpringParameterized {

	@ClassRule
	public static final SpringClassRule RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule rule = new SpringMethodRule();

	protected BindingResult result;

	@ToString
	@RequiredArgsConstructor
	public static class Parameter {
		final public String value;
		final public int errorCount;
	}

	final protected Parameter parameter;

	public SpringParameterized(Parameter parameter) {
		this.parameter = parameter;
	}

	@Before
	public void setUp() throws Exception {
		log.info("{}", parameter);
	}

	@After
	public void tearDown() throws Exception {
		if (result != null) {
			log.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
			result.getFieldErrors().stream().forEach(item -> {
				log.info("{} : {}", item.getField(), item.getDefaultMessage());
			});
		}
		log.info("------------------------------------------------------------");
	}

}
