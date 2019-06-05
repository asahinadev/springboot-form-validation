package com.example.spring.validation.fields.range;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.Fields;
import com.example.spring.form.FromTo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangePojoValidator implements ConstraintValidator<Range, Object> {

	Range annotation;

	@Override
	public void initialize(Range annotation) {

		this.annotation = annotation;
	}

	@RequiredArgsConstructor
	@SuppressWarnings("unchecked")
	private static class CustomFieldsProperty<E> extends Fields<E> {

		final Object field1;

		final Object field2;

		@Override
		public E getField() {

			return (E) field1;
		}

		@Override
		public E getFieldConfime() {

			return (E) field2;
		}
	}

	private <T extends Comparable<? super T>> FromTo<T> fromTo(Object arg0, Object arg1, Class<T> clazz) {

		return FromTo.<T>builder().from(clazz.cast(arg0)).to(clazz.cast(arg1)).build();
	}

	@Override
	public boolean isValid(Object form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}
		BeanWrapper formWrapper = new BeanWrapperImpl(form);

		Object field1 = formWrapper.getPropertyValue(annotation.fieldFrom());
		Object field2 = formWrapper.getPropertyValue(annotation.fieldTo());

		do {
			if (Objects.isNull(field1)) {
				log.debug("field1 : null");
				return true;
			}

			if (field1 instanceof Byte) {
				RangeValidatorByNumberByte validator = new RangeValidatorByNumberByte();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Byte.class), context);
			}

			if (field1 instanceof Short) {
				RangeValidatorByNumberShort validator = new RangeValidatorByNumberShort();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Short.class), context);
			}

			if (field1 instanceof Integer) {
				RangeValidatorByNumberInteger validator = new RangeValidatorByNumberInteger();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Integer.class), context);
			}

			if (field1 instanceof Long) {
				RangeValidatorByNumberLong validator = new RangeValidatorByNumberLong();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Long.class), context);
			}

			if (field1 instanceof Float) {
				RangeValidatorByNumberFloat validator = new RangeValidatorByNumberFloat();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Float.class), context);
			}

			if (field1 instanceof Double) {
				RangeValidatorByNumberDouble validator = new RangeValidatorByNumberDouble();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Double.class), context);
			}

			if (field1 instanceof BigInteger) {
				RangeValidatorByNumberBigInteger validator = new RangeValidatorByNumberBigInteger();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, BigInteger.class), context);
			}

			if (field1 instanceof BigDecimal) {
				RangeValidatorByNumberBigDecimal validator = new RangeValidatorByNumberBigDecimal();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, BigDecimal.class), context);
			}

			if (field1 instanceof Calendar) {
				RangeValidatorByCalendar validator = new RangeValidatorByCalendar();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Calendar.class), context);
			}

			if (field1 instanceof Date) {
				RangeValidatorByDate validator = new RangeValidatorByDate();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Date.class), context);
			}

			if (field1 instanceof LocalDateTime) {
				RangeValidatorByLocalDateTime validator = new RangeValidatorByLocalDateTime();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, LocalDateTime.class), context);
			}

			if (field1 instanceof LocalDate) {
				RangeValidatorByLocalDate validator = new RangeValidatorByLocalDate();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, LocalDate.class), context);
			}

			if (field1 instanceof LocalTime) {
				RangeValidatorByLocalTime validator = new RangeValidatorByLocalTime();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, LocalTime.class), context);
			}

		} while (false);

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldTo())
				.addConstraintViolation();

		return false;

	}

}
