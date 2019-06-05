package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.Fields;

public class FieldDisagreementFormValidator implements ConstraintValidator<FieldDisagreement, Fields<?>> {
	FieldDisagreement annotation;

	@Override
	public void initialize(FieldDisagreement annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Fields<?> form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		Object field1 = form.getField();
		Object field2 = form.getFieldConfime();

		if (!Objects.equals(field1, field2)) {
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldConfime())
				.addConstraintViolation();

		return false;

	}

}
