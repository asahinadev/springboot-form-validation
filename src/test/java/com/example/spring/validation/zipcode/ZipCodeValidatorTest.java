package com.example.spring.validation.zipcode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.validation.*;

import lombok.*;

@SpringBootTest
class ZipCodeValidatorTest {

	@Autowired
	Validator validator;

	@ParameterizedTest
	@ValueSource(strings = {
			"1234567",
			"123-4567"
	})
	void ok1(String value) {
		@Data
		@AllArgsConstructor
		class Zip {
			@ZipCode(fields = "code")
			String code;
		}
		Zip form = new Zip(value);

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 0);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"123456",
			"12345678",
			"1234-5678",
			"123-567"
	})
	void error1(String value) {
		@Data
		@AllArgsConstructor
		class Zip {
			@ZipCode(fields = "code")
			String code;
		}
		Zip form = new Zip(value);

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 1);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"1234567",
			"123-4567"
	})
	void ok2(String value) {
		@Data
		@AllArgsConstructor
		class Zip {
			String code;
		}

		@Data
		@AllArgsConstructor
		class Form {
			@ZipCode(fields = "code")
			Zip zip;
		}

		Form form = new Form(new Zip(value));

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 0);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"123456",
			"12345678",
			"1234-5678",
			"123-567"
	})
	void error2(String value) {
		@Data
		@AllArgsConstructor
		class Zip {
			String code;
		}

		@Data
		@AllArgsConstructor
		class Form {
			@ZipCode(fields = "code")
			Zip zip;
		}

		Form form = new Form(new Zip(value));

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 1);
	}

	@ParameterizedTest
	@CsvSource({
			"123,4567"
	})
	void ok3(String code1, String code2) {
		@Data
		@AllArgsConstructor
		class Zip {
			String code1;
			String code2;
		}

		@Data
		@AllArgsConstructor
		class Form {
			@ZipCode(fields = { "code1", "code2" })
			Zip zip;
		}

		Form form = new Form(new Zip(code1, code2));

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 0);
	}

	@ParameterizedTest
	@CsvSource({
			"1234,1234",
			"123,123",
	})
	void error3(String code1, String code2) {
		@Data
		@AllArgsConstructor
		class Zip {
			String code1;
			String code2;
		}

		@Data
		@AllArgsConstructor
		class Form {
			@ZipCode(fields = { "code1", "code2" })
			Zip zip;
		}

		Form form = new Form(new Zip(code1, code2));

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getErrorCount(), 1);
	}

}
