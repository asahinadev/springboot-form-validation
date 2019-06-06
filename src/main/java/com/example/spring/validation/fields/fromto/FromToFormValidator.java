package com.example.spring.validation.fields.fromto;

import java.util.Objects;

import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;

import com.example.spring.form.FromToType;
import com.example.spring.validation.BasedValidator;

public abstract class FromToFormValidator<E extends Comparable<? super E>>
		extends BasedValidator<FromTo, FromToType<E>> {

	FromTo annotation;

	@Override
	public void initialize(FromTo annotation) {

		this.annotation = annotation;
	}

	abstract Logger log();

	@Override
	public boolean isValid(FromToType<E> form, ConstraintValidatorContext context) {

		E field1 = form.getFrom();
		E field2 = form.getTo();

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

		error(annotation.fieldTo(), context, annotation.message());
		return false;

	}

}
