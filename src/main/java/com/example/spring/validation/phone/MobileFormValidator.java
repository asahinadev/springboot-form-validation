package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

public class MobileFormValidator
		extends BasedValidator<Mobile, PhoneType> {

	Mobile annotation;

	@Override
	public void initialize(Mobile annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// 携帯電話 特有チェック追加用

		return true;
	}
}
