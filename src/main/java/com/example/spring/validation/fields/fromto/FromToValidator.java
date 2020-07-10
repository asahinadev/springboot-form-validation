package com.example.spring.validation.fields.fromto;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.validation.*;

import org.springframework.beans.*;

public class FromToValidator implements ConstraintValidator<FromTo, Object> {

	FromTo annotation;

	@Override
	public void initialize(FromTo annotation) {
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

		if (value1 instanceof Byte) {
			return compalator(value1, value2, Byte.class);
		} else if (value1 instanceof Short) {
			return compalator(value1, value2, Short.class);
		} else if (value1 instanceof Integer) {
			return compalator(value1, value2, Integer.class);
		} else if (value1 instanceof Long) {
			return compalator(value1, value2, Long.class);
		} else if (value1 instanceof Float) {
			return compalator(value1, value2, Float.class);
		} else if (value1 instanceof Double) {
			return compalator(value1, value2, Double.class);
		} else if (value1 instanceof BigInteger) {
			return compalator(value1, value2, BigInteger.class);
		} else if (value1 instanceof BigDecimal) {
			return compalator(value1, value2, BigDecimal.class);
		} else if (value1 instanceof Year) {
			return compalator(value1, value2, Year.class);
		} else if (value1 instanceof YearMonth) {
			return compalator(value1, value2, YearMonth.class);
		} else if (value1 instanceof Month) {
			return compalator(value1, value2, Month.class);
		} else if (value1 instanceof MonthDay) {
			return compalator(value1, value2, MonthDay.class);
		} else if (value1 instanceof ZoneOffset) {
			return compalator(value1, value2, ZoneOffset.class);
		} else if (value1 instanceof ZonedDateTime) {
			return compalator(value1, value2, ZonedDateTime.class);
		} else if (value1 instanceof LocalDateTime) {
			return compalator(value1, value2, LocalDateTime.class);
		} else if (value1 instanceof LocalDate) {
			return compalator(value1, value2, LocalDate.class);
		} else if (value1 instanceof LocalTime) {
			return compalator(value1, value2, LocalTime.class);
		} else if (value1 instanceof OffsetDateTime) {
			return compalator(value1, value2, OffsetDateTime.class);
		} else if (value1 instanceof OffsetTime) {
			return compalator(value1, value2, OffsetTime.class);
		} else if (value1 instanceof Date) {
			return compalator(value1, value2, Date.class);
		} else if (value1 instanceof Calendar) {
			return compalator(value1, value2, Calendar.class);
		} else if (value1 instanceof Instant) {
			return compalator(value1, value2, Instant.class);
		}

		return false;
	}

	private <E extends Comparable<? super E>> boolean compalator(Object field1, Object field2, Class<E> clazz) {
		return compalator(clazz.cast(field1), clazz.cast(field2), clazz);
	}

	private <E extends Comparable<? super E>> boolean compalator(E field1, E field2, Class<E> clazz) {
		return field1.compareTo(field2) <= 0;
	}
}
