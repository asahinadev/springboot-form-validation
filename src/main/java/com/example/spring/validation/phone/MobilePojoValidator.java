package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.Phone;

public class MobilePojoValidator implements ConstraintValidator<Mobile, Object> {

	Mobile annotation;

	@Override
	public void initialize(Mobile annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = new BeanWrapperImpl(value);
		MobileValidator validator = new MobileValidator();
		validator.initialize(annotation);

		return validator.isValid(new Phone(
				(String) form.getPropertyValue(annotation.fieldTel1()),
				(String) form.getPropertyValue(annotation.fieldTel2()),
				(String) form.getPropertyValue(annotation.fieldTel3())), context);
	}

}
