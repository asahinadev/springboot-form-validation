package com.example.spring.validation.fields.fromto;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = FromToValidator.class)
@Repeatable(FromTo.List.class)
@Documented
public @interface FromTo {

	@OverridesAttribute(constraint = Email.class)
	String message() default "{javax.validation.constraints.NotEmpty.message}";

	String[] fields();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		FromTo[] value();
	}
}
