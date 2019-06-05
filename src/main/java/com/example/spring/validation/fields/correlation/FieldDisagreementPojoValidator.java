package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.validation.BasedValidator;

public class FieldDisagreementPojoValidator
		extends BasedValidator<FieldDisagreement, Object> {

	FieldDisagreement annotation;

	@Override
	public void initialize(FieldDisagreement annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = form(value);
		Object field1 = property(form, annotation.field());
		Object field2 = property(form, annotation.fieldConfime());

		if (!Objects.equals(field1, field2)) {
			return true;
		}

		error(annotation.fieldConfime(), context, annotation.message());
		return false;

	}

}
