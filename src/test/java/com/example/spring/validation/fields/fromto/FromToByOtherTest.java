package com.example.spring.validation.fields.fromto;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.form.FromToType;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class FromToByOtherTest
		extends BasedFromToTest<String> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<String>>> data() {

		String c1 = "1";
		String c2 = "2";

		List<ParameterBase<FromToType<String>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<String>builder().from(c1).to(null).build(), 1));
		list.add(new ParameterBase<>(FromToType.<String>builder().from(null).to(c2).build(), 1));
		list.add(new ParameterBase<>(FromToType.<String>builder().from(c1).to(c2).build(), 1));

		return list;
	}

	public FromToByOtherTest(ParameterBase<FromToType<String>> parameter) {

		super(parameter);
	}
}
