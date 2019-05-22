package com.example.spring.validation.fields;

import java.util.Comparator;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;

import com.example.spring.validation.fields.property.FieldsProperty;

public abstract class FieldsRangeComparatorValidator<E>
		implements ConstraintValidator<FieldsRange, FieldsProperty<E>>, Comparator<E> {
	FieldsRange annotation;

	@Override
	public void initialize(FieldsRange annotation) {
		this.annotation = annotation;
	}

	abstract Logger log();

	@Override
	public boolean isValid(FieldsProperty<E> form, ConstraintValidatorContext context) {
		if (form == null) {
			return true;
		}

		E field1 = form.getMain();
		E field2 = form.getSub();

		do {
			if (Objects.isNull(field1)) {
				log().debug("field1 is null");
				return true;
			}

			if (Objects.isNull(field2)) {
				log().debug("field2 is null");
				return true;
			}

			if (annotation.hasEquals() && Objects.equals(field1, field2)) {
				log().debug("field1 = field2");
				return true;
			}

			if (compare(field1, field2) < 0) {
				log().debug("field1 < field2");
				return true;
			}
		} while (false);

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldTo())
				.addConstraintViolation();

		return false;

	}

}
