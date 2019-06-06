package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class PhoneTypeTest {

	@Test
	public void test() {

		PhoneType form = new PhoneType();

		form.setTel1("1");
		form.setTel2("2");
		form.setTel3("3");

		assertThat(form.getTel1(), equalTo("1"));
		assertThat(form.getTel2(), equalTo("2"));
		assertThat(form.getTel3(), equalTo("3"));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		PhoneType.builder().toString();
	}

}
