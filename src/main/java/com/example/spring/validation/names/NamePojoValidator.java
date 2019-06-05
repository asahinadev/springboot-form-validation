package com.example.spring.validation.names;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.form.NameType;
import com.example.spring.validation.BasedValidator;

public class NamePojoValidator
		extends BasedValidator<Name, Object> {

	Name annotation;

	@Override
	public void initialize(Name annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = form(value);
		NameFormValidator validator = new NameFormValidator();
		validator.initialize(annotation);

		return validator.isValid(
				new NameType(
						property(form, annotation.fieldFirstName()),
						property(form, annotation.fieldLastName())),
				context);
	}

}
