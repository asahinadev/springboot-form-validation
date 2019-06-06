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
public class FromToByByteTest
		extends BasedFromToTest<Byte> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Byte>>> data() {

		Byte c1 = 0;
		Byte c2 = 1;

		List<ParameterBase<FromToType<Byte>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(FromToType.<Byte>builder().from(c1).to(c1).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Byte>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Byte>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByByteTest(ParameterBase<FromToType<Byte>> parameter) {

		super(parameter);
	}
}
