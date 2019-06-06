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
public class FromToByDoubleTest2
		extends BasedFromToTest2<Double> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Double>>> data() {

		Double c1 = 0D;
		Double c2 = 1D;

		List<ParameterBase<FromToType<Double>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(FromToType.<Double>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<Double>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Double>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByDoubleTest2(ParameterBase<FromToType<Double>> parameter) {

		super(parameter);
	}
}
