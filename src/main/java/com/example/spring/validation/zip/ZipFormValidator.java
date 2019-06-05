package com.example.spring.validation.zip;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import com.example.spring.form.ZipCode;
import com.example.spring.validation.characters.Numeric;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipFormValidator implements ConstraintValidator<Zip, ZipCode> {

	Zip annotation;

	@Override
	public void initialize(Zip annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(ZipCode form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		String zip1 = form.getZip1();
		String zip2 = form.getZip2();

		String fieldZip1 = annotation.fieldZip1();
		String fieldZip2 = annotation.fieldZip2();

		try {
			do {

				if (StringUtils.isAllEmpty(zip1, zip2)) {
					log.debug("zip is empty");
					return true;
				}

				// 必須チェック
				notEmptyValidator(fieldZip1, zip1, annotation.notEmptyZip1(), context);
				notEmptyValidator(fieldZip2, zip2, annotation.notEmptyZip2(), context);

				// 数値チェック
				numericValidator(fieldZip1, zip1, annotation.numericZip1(), context);
				numericValidator(fieldZip2, zip2, annotation.numericZip2(), context);

				// 桁数チェック
				lengthValidator(fieldZip1, zip1, annotation.lengthZip1(), context);
				lengthValidator(fieldZip2, zip2, annotation.lengthZip2(), context);

			} while (false);
		} catch (RuntimeException e) {
			return false;
		}

		return true;
	}

	void lengthValidator(String field, String value, Length length, ConstraintValidatorContext context) {

		LengthValidator validator = new LengthValidator();
		validator.initialize(length);

		if (!validator.isValid(value, context)) {
			error(field, context, length.message());
			throw new RuntimeException(field);
		}
	}

	void notEmptyValidator(String field, String value, NotEmpty notEmpty, ConstraintValidatorContext context) {

		NotEmptyValidatorForCharSequence validator = new NotEmptyValidatorForCharSequence();
		validator.initialize(notEmpty);

		if (!validator.isValid(value, context)) {
			error(field, context, notEmpty.message());
			throw new RuntimeException(field);
		}
	}

	void numericValidator(String field, String value, Numeric numeric, ConstraintValidatorContext context) {

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

	boolean error(String field, ConstraintValidatorContext context, String message) {

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
