package com.example.spring.validation.phone;

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
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.spring.validation.characters.Numeric;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Telephone.List.class)
@Documented
@Constraint(validatedBy = {
		TelephoneValidator.class,
		TelephonePojoValidator.class
})
public @interface Telephone {

	String message() default "{com.example.spring.validation.Telephone.message}";

	String fieldTel1() default "tel1";

	String fieldTel2() default "tel2";

	String fieldTel3() default "tel3";

	Length lengthTel1() default @Length(min = 3, max = 5);

	Length lengthTel2() default @Length(min = 1, max = 4);

	Length lengthTel3() default @Length(min = 4, max = 4);

	Length lengthTelEntire() default @Length(min = 10, max = 11);

	NotEmpty notEmptyTel1() default @NotEmpty();

	NotEmpty notEmptyTel2() default @NotEmpty();

	NotEmpty notEmptyTel3() default @NotEmpty();

	Numeric numericTel1() default @Numeric();

	Numeric numericTel2() default @Numeric();

	Numeric numericTel3() default @Numeric();

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

		Telephone[] value();
	}
}
