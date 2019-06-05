package com.example.spring.validation;

import java.lang.annotation.Annotation;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.validation.characters.Numeric;

public abstract class BasedValidator<A extends Annotation, T>
		implements ConstraintValidator<A, T> {

	protected BeanWrapper form(Object form) {

		return new BeanWrapperImpl(form);
	}

	@SuppressWarnings("unchecked")
	protected <E> E property(BeanWrapper form, String name) {

		return (E) form.getPropertyValue(name);
	}

	protected void lengthValidator(String field, String value, Length length, ConstraintValidatorContext context) {

		LengthValidator validator = new LengthValidator();
		validator.initialize(length);

		if (!validator.isValid(value, context)) {
			error(field, context, length.message());
			throw new RuntimeException(field);
		}
	}

	protected void notEmptyValidator(String field, String value, NotEmpty notEmpty,
			ConstraintValidatorContext context) {

		NotEmptyValidatorForCharSequence validator = new NotEmptyValidatorForCharSequence();
		validator.initialize(notEmpty);

		if (!validator.isValid(value, context)) {
			error(field, context, notEmpty.message());
			throw new RuntimeException(field);
		}
	}

	protected void numericValidator(String field, String value, Numeric numeric, ConstraintValidatorContext context) {

		try {
			boolean check = Objects.equals(value, Integer.valueOf(value).toString());
			if (check) {
				return;
			}
		} catch (Exception e) {

		}
		error(field, context, numeric.message());
		throw new RuntimeException(field);
	}

	protected boolean error(String field, ConstraintValidatorContext context, String message) {

		context.disableDefaultConstraintViolation();
		ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(message);
		if (field != null) {
			builder.addPropertyNode(field).addConstraintViolation();
		} else {
			builder.addConstraintViolation();
		}
		return false;
	}

}
