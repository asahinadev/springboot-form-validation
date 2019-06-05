package com.example.spring.validation.names;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import com.example.spring.form.NameType;
import com.example.spring.validation.characters.Numeric;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameFormValidator implements ConstraintValidator<Name, NameType> {

	Name annotation;

	@Override
	public void initialize(Name annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(NameType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		String name1 = form.getFirstName();
		String name2 = form.getLastName();

		String fieldName1 = annotation.fieldFirstName();
		String fieldName2 = annotation.fieldLastName();

		try {

			if (StringUtils.isAllEmpty(name1, name2)) {
				log.debug("zip is empty");
				return true;
			}

			// 必須チェック
			notEmptyValidator(fieldName1, name1, annotation.notEmptyFirstName(), context);
			notEmptyValidator(fieldName2, name2, annotation.notEmptyLastName(), context);

			// 桁数チェック
			lengthValidator(fieldName1, name1, annotation.lengthFirstName(), context);
			lengthValidator(fieldName2, name2, annotation.lengthLastName(), context);

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
