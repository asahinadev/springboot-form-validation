package com.example.spring.validation.fields.fromto;

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
@Repeatable(FromTo.List.class)
@Documented
@Constraint(validatedBy = {
		FromToValidatorByNumberByte.class,
		FromToValidatorByNumberShort.class,
		FromToValidatorByNumberInteger.class,
		FromToValidatorByNumberLong.class,
		FromToValidatorByNumberFloat.class,
		FromToValidatorByNumberDouble.class,
		FromToValidatorByNumberBigDecimal.class,
		FromToValidatorByNumberBigInteger.class,

		FromToValidatorByLocalDateTime.class,
		FromToValidatorByLocalDate.class,
		FromToValidatorByLocalTime.class,
		FromToValidatorByCalendar.class,
		FromToValidatorByDate.class

})
public @interface FromTo {

	String message() default "{com.example.spring.validation.fields.fromto.FromTo.message}";

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

		FromTo[] value();
	}
}
