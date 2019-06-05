package com.example.spring.validation.zip;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.example.spring.form.ZipType;
import com.example.spring.validation.BasedValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipFormValidator
		extends BasedValidator<Zip, ZipType> {

	Zip annotation;

	@Override
	public void initialize(Zip annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(ZipType form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		String value1 = form.getZip1();
		String value2 = form.getZip2();

		String field1 = annotation.fieldZip1();
		String field2 = annotation.fieldZip2();

		try {

			if (StringUtils.isAllEmpty(value1, value2)) {
				log.debug("zip is empty");
				return true;
			}

			// 必須チェック
			notEmptyValidator(field1, value1, annotation.notEmptyZip1(), context);
			notEmptyValidator(field2, value2, annotation.notEmptyZip2(), context);

			// 数値チェック
			numericValidator(field1, value1, annotation.numericZip1(), context);
			numericValidator(field2, value2, annotation.numericZip2(), context);

			// 桁数チェック
			lengthValidator(field1, value1, annotation.lengthZip1(), context);
			lengthValidator(field2, value2, annotation.lengthZip2(), context);

		} catch (RuntimeException e) {
			log.debug(e.getMessage(), e);
			return false;
		}

		return true;
	}

}
