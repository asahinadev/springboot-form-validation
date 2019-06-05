package com.example.spring.validation.names;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.NameType;

public class NamePojoValidator implements ConstraintValidator<Name, Object> {

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

		BeanWrapper form = new BeanWrapperImpl(value);
		NameFormValidator validator = new NameFormValidator();
		validator.initialize(annotation);

		return validator.isValid(new NameType(
				(String) form.getPropertyValue(annotation.fieldFirstName()),
				(String) form.getPropertyValue(annotation.fieldLastName())), context);
	}

}
