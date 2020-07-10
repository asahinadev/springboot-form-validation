package com.example.spring.validation.requires;

import javax.validation.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.*;

public class RequiresValidator implements ConstraintValidator<Requires, Object> {

	Requires annotation;

	@Override
	public void initialize(Requires annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		String[] fields = annotation.fields();
		String[] values = new String[fields.length];

		if (fields.length == 0) {
			throw new IllegalStateException("@Requires(fields = {})");
		}

		BeanWrapper form = new BeanWrapperImpl(value);

		for (int i = 0; i < fields.length; i++) {
			values[i] = (String) form.getPropertyValue(fields[i]);
		}

		return StringUtils.isAllEmpty(values)
				|| !StringUtils.isAnyEmpty(values);
	}
}
