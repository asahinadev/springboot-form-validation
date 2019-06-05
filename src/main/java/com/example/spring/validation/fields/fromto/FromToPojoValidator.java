package com.example.spring.validation.fields.fromto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.FromToType;
import com.example.spring.validation.BasedValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToPojoValidator
		extends BasedValidator<FromTo, Object> {

	FromTo annotation;

	@Override
	public void initialize(FromTo annotation) {

		this.annotation = annotation;
	}

	private <T extends Comparable<? super T>> FromToType<T> fromTo(Object arg0, Object arg1, Class<T> clazz) {

		return FromToType.<T>builder().from(clazz.cast(arg0)).to(clazz.cast(arg1)).build();
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
				FromToValidatorByNumberByte validator = new FromToValidatorByNumberByte();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Byte.class), context);
			}

			if (field1 instanceof Short) {
				FromToValidatorByNumberShort validator = new FromToValidatorByNumberShort();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Short.class), context);
			}

			if (field1 instanceof Integer) {
				FromToValidatorByNumberInteger validator = new FromToValidatorByNumberInteger();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Integer.class), context);
			}

			if (field1 instanceof Long) {
				FromToValidatorByNumberLong validator = new FromToValidatorByNumberLong();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Long.class), context);
			}

			if (field1 instanceof Float) {
				FromToValidatorByNumberFloat validator = new FromToValidatorByNumberFloat();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Float.class), context);
			}

			if (field1 instanceof Double) {
				FromToValidatorByNumberDouble validator = new FromToValidatorByNumberDouble();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Double.class), context);
			}

			if (field1 instanceof BigInteger) {
				FromToValidatorByNumberBigInteger validator = new FromToValidatorByNumberBigInteger();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, BigInteger.class), context);
			}

			if (field1 instanceof BigDecimal) {
				FromToValidatorByNumberBigDecimal validator = new FromToValidatorByNumberBigDecimal();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, BigDecimal.class), context);
			}

			if (field1 instanceof Calendar) {
				FromToValidatorByCalendar validator = new FromToValidatorByCalendar();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Calendar.class), context);
			}

			if (field1 instanceof Date) {
				FromToValidatorByDate validator = new FromToValidatorByDate();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, Date.class), context);
			}

			if (field1 instanceof LocalDateTime) {
				FromToValidatorByLocalDateTime validator = new FromToValidatorByLocalDateTime();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, LocalDateTime.class), context);
			}

			if (field1 instanceof LocalDate) {
				FromToValidatorByLocalDate validator = new FromToValidatorByLocalDate();
				validator.initialize(annotation);
				return validator.isValid(fromTo(field1, field2, LocalDate.class), context);
			}

			if (field1 instanceof LocalTime) {
				FromToValidatorByLocalTime validator = new FromToValidatorByLocalTime();
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
