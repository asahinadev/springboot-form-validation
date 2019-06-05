package com.example.spring.validation.fields.correlation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FieldConfimePojoValidator implements ConstraintValidator<FieldConfime, Object> {
	FieldConfime annotation;

	@Override
	public void initialize(FieldConfime annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		BeanWrapper formWrapper = new BeanWrapperImpl(form);

		Object field1 = formWrapper.getPropertyValue(annotation.field());
		Object field2 = formWrapper.getPropertyValue(annotation.fieldConfime());

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
