package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.validation.fields.property.FieldsProperty;

public class FieldsEqualsValidator implements ConstraintValidator<FieldsEquals, FieldsProperty<?>> {
	FieldsEquals annotation;

	@Override
	public void initialize(FieldsEquals annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(FieldsProperty<?> form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		Object field1 = form.getMain();
		Object field2 = form.getSub();

		if (Objects.equals(field1, field2)) {
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldSub())
				.addConstraintViolation();

		return false;

	}

}