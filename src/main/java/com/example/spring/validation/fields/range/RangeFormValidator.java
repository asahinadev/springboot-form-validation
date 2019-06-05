package com.example.spring.validation.fields.range;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;

import com.example.spring.form.FromTo;

public abstract class RangeFormValidator<E extends Comparable<? super E>>
		implements ConstraintValidator<Range, FromTo<E>> {

	Range annotation;

	@Override
	public void initialize(Range annotation) {

		this.annotation = annotation;
	}

	abstract Logger log();

	@Override
	public boolean isValid(FromTo<E> form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		E field1 = form.getFrom();
		E field2 = form.getTo();

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

			if (field1.compareTo(field2) < 0) {
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
