package com.example.spring.validation.fields.fromto;

import java.math.BigDecimal;
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
public class FromToByBigDecimalTest
		extends BasedFromToTest2<BigDecimal> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<BigDecimal>>> data() {

		BigDecimal c1 = BigDecimal.ZERO;
		BigDecimal c2 = BigDecimal.ONE;

		List<ParameterBase<FromToType<BigDecimal>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<BigDecimal>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigDecimal>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigDecimal>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<BigDecimal>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigDecimal>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByBigDecimalTest(ParameterBase<FromToType<BigDecimal>> parameter) {

		super(parameter);
	}
}
