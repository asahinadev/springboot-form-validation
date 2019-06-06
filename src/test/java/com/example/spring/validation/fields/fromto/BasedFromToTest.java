package com.example.spring.validation.fields.fromto;

import com.example.spring.SpringParameterized;
import com.example.spring.form.FromToType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public abstract class BasedFromToTest<
// @formatter:off
E extends Comparable<? super E>

//@formatter:on
>
		extends SpringParameterized<BasedFromToTest.Form<E>, FromToType<E>> {

	public BasedFromToTest(ParameterBase<FromToType<E>> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form<E extends Comparable<? super E>> {

		@FromTo(hasEquals = true)
		final FromToType<E> value;
	}

	@Override
	protected Form<E> createForm() {

		return new Form<>(parameter.value);
	}

}
