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
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.spring.validation.characters.Numeric;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Mobile.List.class)
@Documented
@Constraint(validatedBy = {
		MobileValidator.class,
		MobilePojoValidator.class
})
@Telephone(
		lengthTel1 = @Length(min = 3, max = 3),
		lengthTel2 = @Length(min = 4, max = 4),
		lengthTel3 = @Length(min = 4, max = 4),
		lengthTelEntire = @Length(min = 11, max = 11))
public @interface Mobile {

	String message() default "{com.example.spring.validation.phone.Mobile.message}";

	@OverridesAttribute(constraint = Telephone.class, name = "fieldTel1")
	String fieldTel1();

	@OverridesAttribute(constraint = Telephone.class, name = "fieldTel2")
	String fieldTel2();

	@OverridesAttribute(constraint = Telephone.class, name = "fieldTel3")
	String fieldTel3();

	@OverridesAttribute(constraint = Telephone.class, name = "notEmptyTel1")
	NotEmpty notEmptyTel1() default @NotEmpty();

	@OverridesAttribute(constraint = Telephone.class, name = "notEmptyTel2")
	NotEmpty notEmptyTel2() default @NotEmpty();

	@OverridesAttribute(constraint = Telephone.class, name = "notEmptyTel3")
	NotEmpty notEmptyTel3() default @NotEmpty();

	@OverridesAttribute(constraint = Telephone.class, name = "numericTel1")
	Numeric numericTel1() default @Numeric();

	@OverridesAttribute(constraint = Telephone.class, name = "numericTel2")
	Numeric numericTel2() default @Numeric();

	@OverridesAttribute(constraint = Telephone.class, name = "numericTel3")
	Numeric numericTel3() default @Numeric();

	String[] allowTel1() default {
			"070", "080", "090"
	};

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

		Mobile[] value();
	}
}
