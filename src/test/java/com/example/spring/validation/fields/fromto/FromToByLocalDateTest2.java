package com.example.spring.validation.fields.fromto;

import java.time.LocalDate;
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
public class FromToByLocalDateTest2
		extends BasedFromToTest2<LocalDate> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<LocalDate>>> data() {

		LocalDate c1 = LocalDate.now();
		LocalDate c2 = LocalDate.now().plusDays(1);

		List<ParameterBase<FromToType<LocalDate>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<LocalDate>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDate>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDate>builder().from(c1).to(c1).build(), 1));
		list.add(new ParameterBase<>(FromToType.<LocalDate>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<LocalDate>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByLocalDateTest2(ParameterBase<FromToType<LocalDate>> parameter) {

		super(parameter);
	}
}
