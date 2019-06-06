package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConfimeTypeTest {

	@Test
	public void test() {

		ConfimeType<String> form = new ConfimeType<>();

		form.setField("1");
		form.setFieldConfime("2");

		assertThat(form.getField(), equalTo("1"));
		assertThat(form.getFieldConfime(), equalTo("2"));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		ConfimeType.<String>builder().toString();
	}

}
