package com.example.spring.validation.phone;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import com.example.spring.form.Phone;
import com.example.spring.validation.characters.Numeric;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnFormValidator implements ConstraintValidator<Dn, Phone> {

	Dn annotation;

	@Override
	public void initialize(Dn annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Phone form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		String tel1 = form.getTel1();
		String tel2 = form.getTel2();
		String tel3 = form.getTel3();
		String tels = form.toString();

		String fieldTel1 = annotation.fieldTel1();
		String fieldTel2 = annotation.fieldTel2();
		String fieldTel3 = annotation.fieldTel3();
		String fieldTels = null;

		try {
			do {

				if (StringUtils.isAllEmpty(tel1, tel2, tel3)) {
					log.debug("tel is empty");
					return true;
				}

				// 必須チェック
				notEmptyValidator(fieldTel1, tel1, annotation.notEmptyTel1(), context);
				notEmptyValidator(fieldTel2, tel2, annotation.notEmptyTel2(), context);
				notEmptyValidator(fieldTel3, tel3, annotation.notEmptyTel3(), context);

				// 数値チェック
				numericValidator(fieldTel1, tel1, annotation.numericTel1(), context);
				numericValidator(fieldTel2, tel2, annotation.numericTel2(), context);
				numericValidator(fieldTel3, tel3, annotation.numericTel3(), context);

				// 桁数チェック
				lengthValidator(fieldTel1, tel1, annotation.lengthTel1(), context);
				lengthValidator(fieldTel2, tel2, annotation.lengthTel2(), context);
				lengthValidator(fieldTel3, tel3, annotation.lengthTel3(), context);
				lengthValidator(fieldTels, tels, annotation.lengthTel(), context);

				// ０から始まらない
				if (!tel1.startsWith("0")) {
					error(annotation.fieldTel1(), context, annotation.message());
					throw new RuntimeException(annotation.fieldTel1());
				}

				// 許可された番号
				if (annotation.allowTel1().length != 0
						&& !Arrays.asList(annotation.allowTel1()).contains(tel1)) {
					error(annotation.fieldTel1(), context, annotation.message());
					throw new RuntimeException(annotation.fieldTel1());
				}

				// 許可されていない番号
				if (annotation.denyTel1().length != 0
						&& Arrays.asList(annotation.denyTel1()).contains(tel1)) {
					error(annotation.fieldTel1(), context, annotation.message());
					throw new RuntimeException(annotation.fieldTel1());
				}

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
