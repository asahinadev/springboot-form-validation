package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

public class AnPojoValidator
		extends BasedValidator<An, Object> {

	An annotation;

	@Override
	public void initialize(An annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = form(value);
		AnFormValidator validator = new AnFormValidator();
		validator.initialize(annotation);

		return validator.isValid(
				new PhoneType(
						property(form, annotation.fieldTel1()),
						property(form, annotation.fieldTel2()),
						property(form, annotation.fieldTel3())),
				context);
	}

}
