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

import org.hibernate.validator.constraints.Length;

import com.example.spring.validation.characters.Numeric;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(IpPhone.List.class)
@Documented
@Constraint(validatedBy = {
		IpPhoneFormValidator.class,
		IpPhonePojoValidator.class
})
@Dn
public @interface IpPhone {

	public static final int TEL_1_MIN_LENGTH = 3;

	public static final int TEL_1_MAX_LENGTH = 3;

	public static final int TEL_2_MIN_LENGTH = 4;

	public static final int TEL_2_MAX_LENGTH = 4;

	public static final int TEL_3_MIN_LENGTH = 4;

	public static final int TEL_3_MAX_LENGTH = 4;

	public static final int TEL_MIN_LENGTH = 11;

	public static final int TEL_MAX_LENGTH = 11;

	@OverridesAttribute(constraint = Dn.class, name = "message")
	String message() default "{com.example.spring.validation.phone.An.message}";

	@OverridesAttribute(constraint = Dn.class, name = "fieldTel1")
	String fieldTel1() default "tel1";

	@OverridesAttribute(constraint = Dn.class, name = "fieldTel2")
	String fieldTel2() default "tel2";

	@OverridesAttribute(constraint = Dn.class, name = "fieldTel3")
	String fieldTel3() default "tel3";

	@OverridesAttribute(constraint = Dn.class, name = "lengthTel1")
	Length lengthTel1() default @Length(min = TEL_1_MIN_LENGTH, max = TEL_1_MAX_LENGTH);

	@OverridesAttribute(constraint = Dn.class, name = "lengthTel2")
	Length lengthTel2() default @Length(min = TEL_2_MIN_LENGTH, max = TEL_2_MAX_LENGTH);

	@OverridesAttribute(constraint = Dn.class, name = "lengthTel3")
	Length lengthTel3() default @Length(min = TEL_3_MIN_LENGTH, max = TEL_3_MAX_LENGTH);

	@OverridesAttribute(constraint = Dn.class, name = "lengthTel")
	Length lengthTel() default @Length(min = TEL_MIN_LENGTH, max = TEL_MAX_LENGTH);

	@OverridesAttribute(constraint = Dn.class, name = "notEmptyTel1")
	NotEmpty notEmptyTel1() default @NotEmpty();

	@OverridesAttribute(constraint = Dn.class, name = "notEmptyTel2")
	NotEmpty notEmptyTel2() default @NotEmpty();

	@OverridesAttribute(constraint = Dn.class, name = "notEmptyTel3")
	NotEmpty notEmptyTel3() default @NotEmpty();

	@OverridesAttribute(constraint = Dn.class, name = "numericTel1")
	Numeric numericTel1() default @Numeric();

	@OverridesAttribute(constraint = Dn.class, name = "numericTel2")
	Numeric numericTel2() default @Numeric();

	@OverridesAttribute(constraint = Dn.class, name = "numericTel3")
	Numeric numericTel3() default @Numeric();

	@OverridesAttribute(constraint = Dn.class, name = "allowTel1")
	String[] allowTel1() default {
			"050"
	};

	@OverridesAttribute(constraint = Dn.class, name = "denyTel1")
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

		IpPhone[] value();
	}
}
