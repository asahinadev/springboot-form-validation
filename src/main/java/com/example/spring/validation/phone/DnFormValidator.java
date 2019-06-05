package com.example.spring.validation.phone;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.example.spring.form.PhoneType;
import com.example.spring.validation.BasedValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DnFormValidator
		extends BasedValidator<Dn, PhoneType> {

	Dn annotation;

	@Override
	public void initialize(Dn annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(PhoneType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		String value1 = form.getTel1();
		String value2 = form.getTel2();
		String value3 = form.getTel3();
		String values = form.toString();

		String field1 = annotation.fieldTel1();
		String field2 = annotation.fieldTel2();
		String field3 = annotation.fieldTel3();
		String fields = null;

		try {

			if (StringUtils.isAllEmpty(value1, value2, value3)) {
				log.debug("tel is empty");
				return true;
			}

			// 必須チェック
			notEmptyValidator(field1, value1, annotation.notEmptyTel1(), context);
			notEmptyValidator(field2, value2, annotation.notEmptyTel2(), context);
			notEmptyValidator(field3, value3, annotation.notEmptyTel3(), context);

			// 数値チェック
			numericValidator(field1, value1, annotation.numericTel1(), context);
			numericValidator(field2, value2, annotation.numericTel2(), context);
			numericValidator(field3, value3, annotation.numericTel3(), context);

			// 桁数チェック
			lengthValidator(field1, value1, annotation.lengthTel1(), context);
			lengthValidator(field2, value2, annotation.lengthTel2(), context);
			lengthValidator(field3, value3, annotation.lengthTel3(), context);
			lengthValidator(fields, values, annotation.lengthTel(), context);

			// ０から始まらない
			startsWith(field1, value1, "0", annotation.message(), context);

			// 許可された番号
			allows(field1, value1, annotation.allowTel1(), annotation.message(), context);

			// 許可されていない番号
			denied(field1, value1, annotation.denyTel1(), annotation.message(), context);

		} catch (RuntimeException e) {
			log.debug(e.getMessage(), e);
			return false;
		}

		return true;
	}

}
