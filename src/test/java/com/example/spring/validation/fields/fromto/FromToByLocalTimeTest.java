package com.example.spring.validation.fields.fromto;

import java.time.LocalTime;
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
public class FromToByLocalTimeTest
		extends BasedFromToTest2<LocalTime> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<LocalTime>>> data() {

		LocalTime c1 = LocalTime.now();
		LocalTime c2 = LocalTime.now().plusHours(1);

		List<ParameterBase<FromToType<LocalTime>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<LocalTime>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalTime>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalTime>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<LocalTime>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalTime>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByLocalTimeTest(ParameterBase<FromToType<LocalTime>> parameter) {

		super(parameter);
	}
}
