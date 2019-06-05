package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

public class DnPojoValidator
		extends BasedValidator<Dn, Object> {

	Dn annotation;

	@Override
	public void initialize(Dn annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = form(value);
		DnFormValidator validator = new DnFormValidator();
		validator.initialize(annotation);

		return validator.isValid(
				new PhoneType(
						property(form, annotation.fieldTel1()),
						property(form, annotation.fieldTel2()),
						property(form, annotation.fieldTel3())),
				context);
	}

}
