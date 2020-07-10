package com.example.spring.validation.phone;

import javax.validation.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.*;

public class PhoneValidator implements ConstraintValidator<Phone, Object> {

	Phone annotation;

	@Override
	public void initialize(Phone annotation) {
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
			if (fields.length != 1 && fields.length != 3) {
				throw new IllegalStateException("@ZipCode(fields = {}) size 1 or 3");
			}
			BeanWrapper form = new BeanWrapperImpl(value);
			for (int i = 0; i < fields.length; i++) {
				values[i] = (String) form.getPropertyValue(fields[i]);
			}
		}

		// 入力状態によりチェックを行う
		if (StringUtils.isAllEmpty(values)) {
			// 全項目が未入力なら処理は継続しない
			return true;
		} else if (!StringUtils.isAnyEmpty(values)) {
			// 入力状態がバラバラならばエラーとする
			return false;
		}

		if (values[0].startsWith("0")) {
			// 電話番号は 0 始まり
			return false;
		}

		if (values.length == 3) {
			// 3項目の場合は1項目（ - つなぎに変更）
			values = new String[] {
					String.join("-", values)
			};
		}

		if (StringUtils.equalsAny(values[0], "050", "070", "080", "090")) {
			// 11桁
			return values[0].matches("^\\d{11}$") || values[0].matches("^\\d{3}-\\d{4}-\\d{4}$");
		} else if (StringUtils.equalsAny(values[0], "0120", "0570")) {
			// 10桁
			return values[0].matches("^\\d{10}$") || values[0].matches("^\\d{4}-\\d{3}-\\d{3}$");
		}
		// 電話番号入力パターン
		return false
				|| values[0].matches("^\\d{10}$")

				// 市外局番-市内局番-加入者番号
				|| values[0].matches("^\\d{2}-\\d{4}-\\d{4}$")
				|| values[0].matches("^\\d{3}-\\d{3}-\\d{4}$")
				|| values[0].matches("^\\d{4}-\\d{2}-\\d{4}$")
				|| values[0].matches("^\\d{5}-\\d{1}-\\d{4}$")

				// 市外局番(市内局番)加入者番号
				|| values[0].matches("^\\d{2}\\(\\d{4}\\)\\d{4}$")
				|| values[0].matches("^\\d{3}\\(\\d{3}\\)\\d{4}$")
				|| values[0].matches("^\\d{4}\\(\\d{2}\\)\\d{4}$")
				|| values[0].matches("^\\d{5}\\(\\d{1}\\)\\d{4}$");

	}
}
