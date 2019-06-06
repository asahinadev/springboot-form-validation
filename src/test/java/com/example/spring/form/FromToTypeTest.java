package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class FromToTypeTest {

	@Test
	public void test() {

		FromToType<String> form = new FromToType<>();

		form.setFrom("1");
		form.setTo("2");

		assertThat(form.getFrom(), equalTo("1"));
		assertThat(form.getTo(), equalTo("2"));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		FromToType.<String>builder().toString();
	}

}
