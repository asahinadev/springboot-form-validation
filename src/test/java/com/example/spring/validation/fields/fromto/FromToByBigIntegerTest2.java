package com.example.spring.validation.fields.fromto;

import java.math.BigInteger;
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
public class FromToByBigIntegerTest2
		extends BasedFromToTest2<BigInteger> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<BigInteger>>> data() {

		BigInteger c1 = BigInteger.ZERO;
		BigInteger c2 = BigInteger.ONE;

		List<ParameterBase<FromToType<BigInteger>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<BigInteger>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigInteger>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigInteger>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<BigInteger>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<BigInteger>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByBigIntegerTest2(ParameterBase<FromToType<BigInteger>> parameter) {

		super(parameter);
	}
}
