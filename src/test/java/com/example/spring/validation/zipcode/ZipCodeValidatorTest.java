package com.example.spring.validation.zipcode;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.validation.*;
import org.springframework.validation.Validator;

import lombok.*;

@SpringBootTest
class ZipCodeValidatorTest {

	@AllArgsConstructor
	class Form2 {
		@ZipCode(fields = "code1")
		Zip zip;
	}

	@AllArgsConstructor
	class Form3 {
		@ZipCode(fields = { "code1", "code2" })
		Zip zip;
	}

	@AllArgsConstructor
	class Form4 {
		@ZipCode(fields = { "code1", "code2", "code3" })
		Zip zip;
	}

	class Zip {
		@ZipCode(fields = "code")
		String code;

		@Getter
		String code1;

		@Getter
		String code2;

		public Zip(String code1, String code2) {
			this.code1 = code1;
			this.code2 = code2;
		}

		public Zip(String code1) {
			this.code1 = code1;
		}

		public Zip(String code, boolean a) {
			this.code = code;
		}
	}

	@Autowired
	Validator validator;

	@ParameterizedTest
	@ValueSource(strings = {
			"1234567",
			"123-4567"
	})
	void ok1(String value) {
		check(new Zip(value, false), 0);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"123456",
			"12345678",
			"1234-5678",
			"123-567"
	})
	void error1(String value) {
		check(new Zip(value, false), 1);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"1234567",
			"123-4567"
	})
	void ok2(String value) {
		check(new Form2(new Zip(value)), 0);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"123456",
			"12345678",
			"1234-5678",
			"123-567"
	})
	void error2(String value) {
		check(new Form2(new Zip(value)), 1);
	}

	@ParameterizedTest
	@CsvSource({
			"123,4567"
	})
	void ok3(String code1, String code2) {
		check(new Form3(new Zip(code1, code2)), 0);
	}

	@ParameterizedTest
	@CsvSource({ "1234,1234", "123,123" })
	void error3(String code1, String code2) {
		check(new Form3(new Zip(code1, code2)), 1);
	}

	@Test
	void ok1() {
		ok1(null);
	}

	@Test
	void ok2() {
		ok2(null);
	}

	@Test
	void ok3() {
		ok3(null, null);
	}

	@Test
	void error3() {
		check(new Form3(new Zip("123", null)), 1);
		check(new Form3(new Zip(null, "1234")), 1);
	}

	@Test
	void error4() {
		assertThrows(ValidationException.class,
				() -> check(new Form4(new Zip(null, null)), 1));
	}

	private void check(Object form, int errorCount) {
		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);
		assertEquals(r.getErrorCount(), errorCount);
	}
}
