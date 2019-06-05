package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

public class AnFormValidator
		extends BasedValidator<An, PhoneType> {

	An annotation;

	@Override
	public void initialize(An annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// an 特有チェック追加用

		return true;
	}
}
