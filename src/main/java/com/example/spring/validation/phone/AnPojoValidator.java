package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.Phone;

public class AnPojoValidator implements ConstraintValidator<An, Object> {

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

		BeanWrapper form = new BeanWrapperImpl(value);
		AnFormValidator validator = new AnFormValidator();
		validator.initialize(annotation);

		return validator.isValid(new Phone(
				(String) form.getPropertyValue(annotation.fieldTel1()),
				(String) form.getPropertyValue(annotation.fieldTel2()),
				(String) form.getPropertyValue(annotation.fieldTel3())), context);
	}

}
