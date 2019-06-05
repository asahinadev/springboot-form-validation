package com.example.spring.validation.names;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.example.spring.form.NameType;
import com.example.spring.validation.BasedValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameFormValidator
		extends BasedValidator<Name, NameType> {

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

		String value1 = form.getFirstName();
		String value2 = form.getLastName();

		String field1 = annotation.fieldFirstName();
		String field2 = annotation.fieldLastName();

		try {

			if (StringUtils.isAllEmpty(value1, value2)) {
				log.debug("zip is empty");
				return true;
			}

			// 必須チェック
			notEmptyValidator(field1, value1, annotation.notEmptyFirstName(), context);
			notEmptyValidator(field2, value2, annotation.notEmptyLastName(), context);

			// 桁数チェック
			lengthValidator(field1, value1, annotation.lengthFirstName(), context);
			lengthValidator(field2, value2, annotation.lengthLastName(), context);

		} catch (RuntimeException e) {
			log.debug(e.getMessage(), e);
			return false;
		}

		return true;
	}

}
