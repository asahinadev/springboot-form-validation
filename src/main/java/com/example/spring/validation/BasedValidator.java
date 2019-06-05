package com.example.spring.validation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForDouble;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MinValidatorForDouble;
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

	protected void notNullValidator(String field, Object value, NotNull notNull, ConstraintValidatorContext context) {

		NotNullValidator validator = new NotNullValidator();
		validator.initialize(notNull);

		if (!validator.isValid(value, context)) {
			error(field, context, notNull.message());
			throw new RuntimeException(field);
		}
	}

	protected void minValidator(String field, Double value, Min min, ConstraintValidatorContext context) {

		MinValidatorForDouble validator = new MinValidatorForDouble();
		validator.initialize(min);

		if (!validator.isValid(value, context)) {
			error(field, context, min.message());
			throw new RuntimeException(field);
		}
	}

	protected void maxValidator(String field, Double value, Max max, ConstraintValidatorContext context) {

		MaxValidatorForDouble validator = new MaxValidatorForDouble();
		validator.initialize(max);

		if (!validator.isValid(value, context)) {
			error(field, context, max.message());
			throw new RuntimeException(field);
		}
	}

	protected boolean allows(String field, String value, String[] allows, String message,
			ConstraintValidatorContext context) {

		// 許可された文字列
		if (allows.length != 0 && !Arrays.asList(allows).contains(value)) {
			error(field, context, message);
			throw new RuntimeException(field);
		}
		return true;
	}

	protected boolean denied(String field, String value, String[] denied, String message,
			ConstraintValidatorContext context) {

		// 許可されていない文字列
		if (denied.length != 0 && Arrays.asList(denied).contains(value)) {
			error(field, context, message);
			throw new RuntimeException(field);
		}
		return true;
	}

	protected boolean startsWith(String field, String value, String prefix, String message,
			ConstraintValidatorContext context) {

		// 許可された文字列
		if (!value.startsWith(prefix)) {
			error(field, context, message);
			throw new RuntimeException(field);
		}
		return true;
	}

	protected boolean endsWith(String field, String value, String suffix, String message,
			ConstraintValidatorContext context) {

		// 許可された文字列
		if (!value.endsWith(suffix)) {
			error(field, context, message);
			throw new RuntimeException(field);
		}
		return true;
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
