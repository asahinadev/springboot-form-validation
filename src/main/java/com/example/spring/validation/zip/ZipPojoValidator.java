package com.example.spring.validation.zip;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.form.ZipType;
import com.example.spring.validation.BasedValidator;

public class ZipPojoValidator
		extends BasedValidator<Zip, Object> {

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

		BeanWrapper form = form(value);

		ZipFormValidator validator = new ZipFormValidator();
		validator.initialize(annotation);

		return validator.isValid(
				new ZipType(
						property(form, annotation.fieldZip1()),
						property(form, annotation.fieldZip2())),
				context);
	}

}
