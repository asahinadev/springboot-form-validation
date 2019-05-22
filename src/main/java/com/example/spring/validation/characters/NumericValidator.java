package com.example.spring.validation.characters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class NumericValidator
		implements ConstraintValidator<Numeric, String> {

	Numeric annotation;

	@Override
	public void initialize(Numeric annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isEmpty(value) ||
				StringUtils.isAsciiPrintable(value) &&
						StringUtils.isNumeric(value);
	}

}
