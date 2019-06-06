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
public class FromToByLongTest2
		extends BasedFromToTest2<Long> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Long>>> data() {

		Long c1 = 0L;
		Long c2 = 1L;

		List<ParameterBase<FromToType<Long>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(FromToType.<Long>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<Long>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Long>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByLongTest2(ParameterBase<FromToType<Long>> parameter) {

		super(parameter);
	}
}
