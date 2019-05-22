package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.validation.phone.property.PhoneProperty;

public class ApplicationNumberPojoValidator implements ConstraintValidator<ApplicationNumber, Object> {
	ApplicationNumber annotation;

	@Override
	public void initialize(ApplicationNumber annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		BeanWrapper form = new BeanWrapperImpl(value);
		ApplicationNumberValidator validator = new ApplicationNumberValidator();
		validator.initialize(annotation);

		return validator.isValid(new PhoneProperty() {

			@Override
			public String getTel1() {
				return (String) form.getPropertyValue(annotation.fieldTel1());
			}

			@Override
			public String getTel2() {
				return (String) form.getPropertyValue(annotation.fieldTel2());
			}

			@Override
			public String getTel3() {
				return (String) form.getPropertyValue(annotation.fieldTel3());
			}
		}, context);

	}

}
