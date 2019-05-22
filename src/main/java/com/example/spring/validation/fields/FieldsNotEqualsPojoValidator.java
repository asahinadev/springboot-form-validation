package com.example.spring.validation.fields;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsNotEqualsPojoValidator implements ConstraintValidator<FieldsNotEquals, Object> {
	FieldsNotEquals annotation;

	@Override
	public void initialize(FieldsNotEquals annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		BeanWrapper formWrapper = new BeanWrapperImpl(form);

		Object field1 = formWrapper.getPropertyValue(annotation.fieldMain());
		Object field2 = formWrapper.getPropertyValue(annotation.fieldSub());

		if (!Objects.equals(field1, field2)) {
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldSub())
				.addConstraintViolation();

		return false;

	}

}
