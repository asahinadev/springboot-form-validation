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
@Repeatable(Dn.List.class)
@Documented
@Constraint(validatedBy = {
		DnFormValidator.class,
		DnPojoValidator.class
})
public @interface Dn {

	public static final int TEL_1_MIN_LENGTH = 2;

	public static final int TEL_1_MAX_LENGTH = 5;

	public static final int TEL_2_MIN_LENGTH = 1;

	public static final int TEL_2_MAX_LENGTH = 4;

	public static final int TEL_3_MIN_LENGTH = 4;

	public static final int TEL_3_MAX_LENGTH = 4;

	public static final int TEL_MIN_LENGTH = 10;

	public static final int TEL_MAX_LENGTH = 11;

	String message() default "{com.example.spring.validation.phone.Dn.message}";

	String fieldTel1() default "tel1";

	String fieldTel2() default "tel2";

	String fieldTel3() default "tel3";

	Length lengthTel1() default @Length(min = TEL_1_MIN_LENGTH, max = TEL_1_MAX_LENGTH);

	Length lengthTel2() default @Length(min = TEL_2_MIN_LENGTH, max = TEL_2_MAX_LENGTH);

	Length lengthTel3() default @Length(min = TEL_3_MIN_LENGTH, max = TEL_3_MAX_LENGTH);

	Length lengthTel() default @Length(min = TEL_MIN_LENGTH, max = TEL_MAX_LENGTH);

	NotEmpty notEmptyTel1() default @NotEmpty();

	NotEmpty notEmptyTel2() default @NotEmpty();

	NotEmpty notEmptyTel3() default @NotEmpty();

	Numeric numericTel1() default @Numeric();

	Numeric numericTel2() default @Numeric();

	Numeric numericTel3() default @Numeric();

	String[] allowTel1() default {
	};

	String[] denyTel1() default {
	};

	Class<?>[] groups() default {
	};

	Class<? extends Payload>[] payload() default {

	};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Dn[] value();
	}
}
