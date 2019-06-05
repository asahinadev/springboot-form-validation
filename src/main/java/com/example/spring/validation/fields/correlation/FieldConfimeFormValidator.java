package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.ConfimeType;
import com.example.spring.validation.BasedValidator;

public class FieldConfimeFormValidator
		extends BasedValidator<FieldConfime, ConfimeType<?>> {

	FieldConfime annotation;

	@Override
	public void initialize(FieldConfime annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(ConfimeType<?> form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		Object field1 = form.getField();
		Object field2 = form.getFieldConfime();

		if (Objects.equals(field1, field2)) {
			return true;
		}

		error(annotation.fieldConfime(), context, annotation.message());
		return false;

	}

}
