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

		if (!StringUtils.contains(value, "@")) {
			return true;
		}

		for (String domain : mobileMailConfig.denied) {
			String target = value.substring(value.length() - domain.length());

			if (StringUtils.equals(target, domain)) {
				return false;
			}
		}

		for (String domain : annotation.denied()) {
			String target = value.substring(value.length() - domain.length());

			if (StringUtils.equals(target, domain)) {
				return false;
			}
		}

		for (String domain : mobileMailConfig.allows) {
			String target = value.substring(value.length() - domain.length());

			if (StringUtils.equals(target, domain)) {
				return false;
			}
		}

		for (String domain : annotation.allows()) {
			String target = value.substring(value.length() - domain.length());

			if (StringUtils.equals(target, domain)) {
				return false;
			}
		}

		return false;
	}

}
