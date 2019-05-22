package com.example.spring.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import com.example.spring.validation.characters.Numeric;
import com.example.spring.validation.characters.NumericValidator;
import com.example.spring.validation.phone.property.PhoneProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelephoneValidator implements ConstraintValidator<Telephone, PhoneProperty> {

	Telephone annotation;

	@Override
	public void initialize(Telephone annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneProperty form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		String tel1 = form.getTel1();
		String tel2 = form.getTel2();
		String tel3 = form.getTel3();

		try {
			do {

				if (StringUtils.isAllEmpty(tel1, tel2, tel3)) {
					log.debug("tel is empty");
					return true;
				}

				// 各桁が未入力が混在
				notEmptyValidator(annotation.fieldTel1(), tel1, annotation.notEmptyTel1(), context);
				notEmptyValidator(annotation.fieldTel2(), tel2, annotation.notEmptyTel2(), context);
				notEmptyValidator(annotation.fieldTel3(), tel3, annotation.notEmptyTel3(), context);

				// 各桁が数値ではない
				numericValidator(annotation.fieldTel1(), tel1, annotation.numericTel1(), context);
				numericValidator(annotation.fieldTel2(), tel2, annotation.numericTel2(), context);
				numericValidator(annotation.fieldTel3(), tel3, annotation.numericTel3(), context);

				// 桁数チェック
				lengthValidator(annotation.fieldTel1(), tel1, annotation.lengthTel1(), context);
				lengthValidator(annotation.fieldTel2(), tel2, annotation.lengthTel2(), context);
				lengthValidator(annotation.fieldTel3(), tel3, annotation.lengthTel3(), context);
				lengthValidator(null, form.getTelEntire(), annotation.lengthTelEntire(), context);

				// ０から始まらない
				if (!tel1.startsWith("0")) {
					log.debug("tel1 is not zero start");
					return error(annotation.fieldTel1(), context, annotation.message());
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
		NumericValidator validator = new NumericValidator();
		validator.initialize(numeric);

		if (!validator.isValid(value, context)) {
			error(field, context, numeric.message());
			throw new RuntimeException(field);
		}
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
