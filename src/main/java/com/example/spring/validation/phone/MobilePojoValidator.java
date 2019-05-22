package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.validation.phone.property.PhoneProperty;

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
