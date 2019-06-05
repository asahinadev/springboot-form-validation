package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

public class IpPhoneFormValidator
		extends BasedValidator<IpPhone, PhoneType> {

	IpPhone annotation;

	@Override
	public void initialize(IpPhone annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		// ip 電話 特有チェック追加用

		return true;
	}
}
