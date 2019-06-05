package com.example.spring.validation.zip;

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
@Repeatable(Zip.List.class)
@Documented
@Constraint(validatedBy = {
		ZipFormValidator.class,
		ZipPojoValidator.class
})
public @interface Zip {

	public static final int TEL_1_MIN_LENGTH = 3;

	public static final int TEL_1_MAX_LENGTH = 3;

	public static final int TEL_2_MIN_LENGTH = 4;

	public static final int TEL_2_MAX_LENGTH = 4;

	String message() default "{com.example.spring.validation.phone.Dn.message}";

	String fieldZip1() default "zip1";

	String fieldZip2() default "zip2";

	Length lengthZip1() default @Length(min = TEL_1_MIN_LENGTH, max = TEL_1_MAX_LENGTH);

	Length lengthZip2() default @Length(min = TEL_2_MIN_LENGTH, max = TEL_2_MAX_LENGTH);

	NotEmpty notEmptyZip1() default @NotEmpty();

	NotEmpty notEmptyZip2() default @NotEmpty();

	Numeric numericZip1() default @Numeric();

	Numeric numericZip2() default @Numeric();

	Class<?>[] groups() default {
	};

	Class<? extends Payload>[] payload() default {

	};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Zip[] value();
	}
}
