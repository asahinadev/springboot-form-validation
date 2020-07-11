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
	void test(String value) {
		@AllArgsConstructor
		class Zip {
			@ZipCode(fields = "code")
			String code;
		}
		Zip form = new Zip(value);

		BindingResult r = new BindException(form, "form");
		validator.validate(form, r);

		assertEquals(r.getFieldErrorCount(), 0);
		assertEquals(r.getFieldErrorCount("code"), 0);
		assertEquals(r.getFieldErrorCount("form.code"), 0);
	}

}
