package com.example.spring.validation.zipcode;

import java.util.regex.*;

import javax.validation.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.*;

import lombok.extern.slf4j.*;

@Slf4j
public class ZipCodeValidator implements ConstraintValidator<ZipCode, Object> {

	ZipCode annotation;

	@Override
	public void initialize(ZipCode annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		String[] fields = annotation.fields();
		String[] values = new String[fields.length];

		if (value instanceof String) {
			fields = new String[] { "form" };
			values = new String[] { value.toString() };
		} else {
			if (fields.length != 1 && fields.length != 2) {
				throw new IllegalStateException("@ZipCode(fields = {}) size 1 or 2");
			}
			BeanWrapper form = new BeanWrapperImpl(value);
			for (int i = 0; i < fields.length; i++) {
				values[i] = (String) form.getPropertyValue(fields[i]);
			}
		}

		// 入力状態によりチェックを行う
		if (StringUtils.isAllEmpty(values)) {
			// 全項目が未入力なら処理は継続しない
			log.debug("isAllEmpty {} {}", fields, values);
			return true;
		} else if (StringUtils.isAnyEmpty(values)) {
			// 入力状態がバラバラならばエラーとする
			log.debug("isAnyEmpty {} {}", fields, values);
			return false;
		}

		String test = String.join("-", values);

		log.debug("test ={}", test);
		return Pattern.matches("^\\d{3}-?\\d{4}$", test);
	}
}
