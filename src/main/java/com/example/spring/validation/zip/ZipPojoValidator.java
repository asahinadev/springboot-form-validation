package com.example.spring.validation.zip;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.ZipCode;

public class ZipPojoValidator implements ConstraintValidator<Zip, Object> {

	Zip annotation;

	@Override
	public void initialize(Zip annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = new BeanWrapperImpl(value);
		ZipFormValidator validator = new ZipFormValidator();
		validator.initialize(annotation);

		return validator.isValid(new ZipCode(
				(String) form.getPropertyValue(annotation.fieldZip1()),
				(String) form.getPropertyValue(annotation.fieldZip2())), context);
	}

}
