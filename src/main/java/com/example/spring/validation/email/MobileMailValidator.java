package com.example.spring.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MobileMailValidator
		implements ConstraintValidator<MobileMail, String> {

	MobileMail annotation;

	@Autowired
	MobileMailConfig mobileMailConfig;

	@Override
	public void initialize(MobileMail annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(value)) {
			return true;
		}

		for (String domain : mobileMailConfig.denied) {
			if (value.endsWith(domain)) {
				return false;
			}
		}

		for (String domain : mobileMailConfig.allows) {
			if (value.endsWith(domain)) {
				return true;
			}
		}

		return false;
	}

}
