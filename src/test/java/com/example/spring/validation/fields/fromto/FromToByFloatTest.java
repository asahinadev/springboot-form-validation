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
public class FromToByFloatTest
		extends BasedFromToTest<Float> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Float>>> data() {

		Float c1 = 0F;
		Float c2 = 1F;

		List<ParameterBase<FromToType<Float>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(FromToType.<Float>builder().from(c1).to(c1).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Float>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Float>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByFloatTest(ParameterBase<FromToType<Float>> parameter) {

		super(parameter);
	}
}
