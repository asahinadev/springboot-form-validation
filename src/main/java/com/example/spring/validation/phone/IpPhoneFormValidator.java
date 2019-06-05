package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.Phone;

public class IpPhoneFormValidator implements ConstraintValidator<IpPhone, Phone> {

	IpPhone annotation;

	@Override
	public void initialize(IpPhone annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Phone form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// ip 電話 特有チェック追加用

		return true;
	}
}
