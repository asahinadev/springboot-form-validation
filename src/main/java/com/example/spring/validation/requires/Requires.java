package com.example.spring.validation.requires;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = RequiresValidator.class)
@Repeatable(Requires.List.class)
@Documented
public @interface Requires {

	@OverridesAttribute(constraint = Email.class)
	String message() default "{javax.validation.constraints.NotEmpty.message}";

	String[] fields();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		Requires[] value();
	}
}
