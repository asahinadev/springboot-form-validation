package com.example.spring.validation.location;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Location.List.class)
@Documented
@Constraint(validatedBy = {
		LocationFormValidator.class,
		LocationPojoValidator.class
})
public @interface Location {

	String message() default "{com.example.spring.validation.location.Location.message}";

	String fieldLat() default "zip1";

	String fieldLng() default "zip2";

	NotNull notNullLat() default @NotNull();

	NotNull notNullLng() default @NotNull();

	Min minLat() default @Min(20);

	Min minLng() default @Min(122);

	Max maxLat() default @Max(46);

	Max maxLng() default @Max(154);

	Class<?>[] groups() default {
	};

	Class<? extends Payload>[] payload() default {

	};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Location[] value();
	}
}
