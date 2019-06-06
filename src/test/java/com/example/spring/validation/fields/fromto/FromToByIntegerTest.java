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
public class FromToByIntegerTest
		extends BasedFromToTest<Integer> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Integer>>> data() {

		Integer c1 = 0;
		Integer c2 = 1;

		List<ParameterBase<FromToType<Integer>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<Integer>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Integer>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Integer>builder().from(c1).to(c1).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Integer>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Integer>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByIntegerTest(ParameterBase<FromToType<Integer>> parameter) {

		super(parameter);
	}
}
