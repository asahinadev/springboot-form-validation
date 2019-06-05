package com.example.spring.validation.fields.range;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Range.List.class)
@Documented
@Constraint(validatedBy = {
		RangeValidatorByNumberByte.class,
		RangeValidatorByNumberShort.class,
		RangeValidatorByNumberInteger.class,
		RangeValidatorByNumberLong.class,
		RangeValidatorByNumberFloat.class,
		RangeValidatorByNumberDouble.class,
		RangeValidatorByNumberBigDecimal.class,
		RangeValidatorByNumberBigInteger.class,

		RangeValidatorByLocalDateTime.class,
		RangeValidatorByLocalDate.class,
		RangeValidatorByLocalTime.class,
		RangeValidatorByCalendar.class,
		RangeValidatorByDate.class

})
public @interface Range {

	String message() default "{com.example.spring.validation.fields.range.Range.message}";

	String fieldFrom();

	String fieldTo();

	boolean hasEquals() default true;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link NotNull} annotations on the same element.
	 *
	 * @see javax.validation.constraints.NotNull
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Range[] value();
	}
}
