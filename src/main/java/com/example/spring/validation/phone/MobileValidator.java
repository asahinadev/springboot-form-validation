package com.example.spring.validation.phone;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.example.spring.validation.phone.property.PhoneProperty;

public class MobileValidator implements ConstraintValidator<Mobile, PhoneProperty> {

	Mobile annotation;

	@Override
	public void initialize(Mobile annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneProperty form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		String tel1 = form.getTel1();
		if (StringUtils.isEmpty(tel1)) {
			return true;
		}

		return Arrays.asList(annotation.allowTel1()).contains(tel1);
	}
}
