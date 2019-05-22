package com.example.spring.validation.characters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class AsciiValidator
		implements ConstraintValidator<Ascii, String> {

	Ascii annotation;

	@Override
	public void initialize(Ascii annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isEmpty(value) || StringUtils.isAsciiPrintable(value);
	}

}
