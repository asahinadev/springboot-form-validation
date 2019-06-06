package com.example.spring.validation.characters;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class AlphanumTest extends SpringParameterized<AlphanumTest.Form> {

	@Parameterized.Parameters
	public static List<Parameter> data() {

		List<Parameter> list = new ArrayList<>();

		// 英小文字
		for (char value : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
			list.add(new Parameter(value + "", 0));
		}
		// 英大文字
		for (char value : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			list.add(new Parameter(value + "", 0));
		}
		// 数字
		for (char value : "1234567890".toCharArray()) {
			list.add(new Parameter(value + "", 0));
		}
		// 記号
		for (char value : "!\"#$%&'()=-~^|\\`@[{+;*:}]<,>.?/_".toCharArray()) {
			list.add(new Parameter(value + "", 1));
		}
		// マルチバイト文字
		list.add(new Parameter("あいうえお", 1));
		list.add(new Parameter("アイウエオ", 1));
		list.add(new Parameter("亜衣卯絵尾", 1));

		return list;
	}

	public AlphanumTest(Parameter parameter) {

		super(parameter);
	}

	@RequiredArgsConstructor
	public static class Form {

		@Alphanum
		final String value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
