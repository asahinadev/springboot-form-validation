package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class NameTypeTest {

	@Test
	public void test() {

		NameType form = new NameType();

		form.setFirstName("1");
		form.setLastName("2");

		assertThat(form.getFirstName(), equalTo("1"));
		assertThat(form.getLastName(), equalTo("2"));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		NameType.builder().toString();
	}

}
