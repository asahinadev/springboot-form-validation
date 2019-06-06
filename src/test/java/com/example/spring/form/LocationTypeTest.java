package com.example.spring.form;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LocationTypeTest {

	@Test
	public void test() {

		LocationType form = new LocationType();

		form.setLat(1D);
		form.setLng(2D);

		assertThat(form.getLat(), equalTo(1D));
		assertThat(form.getLng(), equalTo(2D));

	}

	@Test
	public void testBuilder() {

		// 動作確認のみ
		LocationType.builder().toString();
	}

}
