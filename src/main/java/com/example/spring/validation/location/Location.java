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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
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

	public static final String JP_LAT_MIN = "20";

	public static final String JP_LAT_MAX = "46";

	public static final String JP_LNG_MIN = "122";

	public static final String JP_LNG_MAX = "154";

	String message() default "{com.example.spring.validation.location.Location.message}";

	String fieldLat() default "lat";

	String fieldLng() default "lng";

	NotNull notNullLat() default @NotNull();

	NotNull notNullLng() default @NotNull();

	DecimalMin minLat() default @DecimalMin(value = JP_LAT_MIN);

	DecimalMin minLng() default @DecimalMin(value = JP_LNG_MIN);

	DecimalMax maxLat() default @DecimalMax(value = JP_LAT_MAX);

	DecimalMax maxLng() default @DecimalMax(value = JP_LNG_MAX);

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
