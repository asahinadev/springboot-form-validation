package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.Phone;

public class MobileValidator implements ConstraintValidator<Mobile, Phone> {

	Mobile annotation;

	@Override
	public void initialize(Mobile annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Phone form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// 携帯電話 特有チェック追加用

		return true;
	}
}
