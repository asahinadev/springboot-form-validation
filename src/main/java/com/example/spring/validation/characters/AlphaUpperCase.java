package com.example.spring.validation.characters;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Repeatable(AlphaUpperCase.List.class)
@Documented
@Pattern(regexp = "^[\\p{Upper}]*$")
public @interface AlphaUpperCase {

	@OverridesAttribute(constraint = Pattern.class)
	String message() default "{com.example.spring.validation.characters.AlphaUpperCase.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {

		AlphaUpperCase[] value();
	}
}
