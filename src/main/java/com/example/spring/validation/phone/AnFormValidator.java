package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.Phone;

public class AnFormValidator implements ConstraintValidator<An, Phone> {

	An annotation;

	@Override
	public void initialize(An annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Phone form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// an 特有チェック追加用

		return true;
	}
}
