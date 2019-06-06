package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ZipTypeTest {

	@Test
	public void test() {

		ZipType form = new ZipType();

		form.setZip1("1");
		form.setZip2("2");

		assertThat(form.getZip1(), equalTo("1"));
		assertThat(form.getZip2(), equalTo("2"));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		ZipType.builder().toString();
	}

}
