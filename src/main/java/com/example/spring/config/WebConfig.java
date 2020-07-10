package com.example.spring.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.beanvalidation.*;

import lombok.extern.slf4j.*;

@Slf4j
@Configuration
public class WebConfig {

	@Autowired
	MessageSource messageSource;

	@Bean
	public Validator getValidator() {

		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		log.debug("messageSource {}", messageSource);
		return validator;
	}

}
