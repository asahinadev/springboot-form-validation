package com.example.spring.validation.fields;

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

import com.example.spring.validation.fields.property.FieldsProperty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangePojoValidator implements ConstraintValidator<FieldsRange, Object> {
	FieldsRange annotation;

	@Override
	public void initialize(FieldsRange annotation) {
		this.annotation = annotation;
	}

	@RequiredArgsConstructor
	@SuppressWarnings("unchecked")
	private static class CustomFieldsProperty<E> implements FieldsProperty<E> {
		final Object field1;
		final Object field2;

		@Override
		public E getMain() {
			return (E) field1;
		}

		@Override
		public E getSub() {
			return (E) field2;
		}
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
				FieldsRangeByteValidator validator = new FieldsRangeByteValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Short) {
				FieldsRangeShortValidator validator = new FieldsRangeShortValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Integer) {
				FieldsRangeIntegerValidator validator = new FieldsRangeIntegerValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Long) {
				FieldsRangeLongValidator validator = new FieldsRangeLongValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Float) {
				FieldsRangeFloatValidator validator = new FieldsRangeFloatValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Double) {
				FieldsRangeDoubleValidator validator = new FieldsRangeDoubleValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof BigInteger) {
				FieldsRangeBigIntegerValidator validator = new FieldsRangeBigIntegerValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof BigDecimal) {
				FieldsRangeBigDecimalValidator validator = new FieldsRangeBigDecimalValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof LocalDateTime) {
				FieldsRangeLocalDateTimeValidator validator = new FieldsRangeLocalDateTimeValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof LocalDate) {
				FieldsRangeLocalDateValidator validator = new FieldsRangeLocalDateValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof LocalTime) {
				FieldsRangeLocalTimeValidator validator = new FieldsRangeLocalTimeValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Calendar) {
				FieldsRangeCalendarValidator validator = new FieldsRangeCalendarValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

			if (field1 instanceof Date) {
				FieldsRangeDateValidator validator = new FieldsRangeDateValidator();
				validator.initialize(annotation);
				return validator.isValid(new CustomFieldsProperty<>(field1, field2), context);
			}

		} while (false);

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(annotation.message())
				.addPropertyNode(annotation.fieldTo())
				.addConstraintViolation();

		return false;

	}

}
