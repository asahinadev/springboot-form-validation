package com.example.spring.validation.fields.correlation;

import java.util.*;

import javax.validation.*;

import org.springframework.beans.*;

public class EqualsFieldsValidator implements ConstraintValidator<EqualsFields, Object> {

	EqualsFields annotation;

	@Override
	public void initialize(EqualsFields annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		String[] fields = annotation.fields();

		if (fields.length != 2) {
			throw new IllegalStateException("@Requires(fields = {})");
		}

		BeanWrapper form = new BeanWrapperImpl(value);

		Object value1 = form.getPropertyValue(fields[0]);
		Object value2 = form.getPropertyValue(fields[1]);

		Class<?> clazz1 = form.getPropertyType(fields[0]);
		Class<?> clazz2 = form.getPropertyType(fields[1]);

		if (Objects.isNull(value1) && Objects.isNull(value1)) {
			// 全ての項目が null
			return true;
		} else if (Objects.isNull(value1) || Objects.isNull(value2)) {
			// 入力状態がバラバラならばエラーとする
			return false;
		} else if (!clazz1.equals(clazz2)) {
			// 同一クラスではない場合はエラー
			return false;
		}

		return Objects.equals(value1, value2);
	}
}
