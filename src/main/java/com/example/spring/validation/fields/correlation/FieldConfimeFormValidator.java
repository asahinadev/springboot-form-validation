package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.spring.config.Fieldset;

public class ConfimeFormValidator implements ConstraintValidator<Confime, Fieldset<?>> {
	Confime annotation;

	@Override
	public void initialize(Confime annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Fieldset<?> form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		Object field1 = form.getField();
		Object field2 = form.getFieldConfime();

		if (Objects.equals(field1, field2)) {
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldConfime())
				.addConstraintViolation();

		return false;

	}

}
