package com.example.spring.validation.fields.fromto;

import java.time.LocalDateTime;
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
public class FromToByLocalDateTimeTest
		extends BasedFromToTest<LocalDateTime> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<LocalDateTime>>> data() {

		LocalDateTime c1 = LocalDateTime.now();
		LocalDateTime c2 = LocalDateTime.now().plusDays(1);

		List<ParameterBase<FromToType<LocalDateTime>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<LocalDateTime>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDateTime>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDateTime>builder().from(c1).to(c1).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDateTime>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDateTime>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByLocalDateTimeTest(ParameterBase<FromToType<LocalDateTime>> parameter) {

		super(parameter);
	}
}
