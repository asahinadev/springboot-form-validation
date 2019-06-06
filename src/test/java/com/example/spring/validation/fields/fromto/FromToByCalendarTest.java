package com.example.spring.validation.fields.fromto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class FromToByCalendarTest
		extends BasedFromToTest<Calendar> {

	@Parameterized.Parameters
	public static List<ParameterBase<FromToType<Calendar>>> data() {

		Calendar c1 = new GregorianCalendar(2019, 1, 1);
		Calendar c2 = new GregorianCalendar(2019, 1, 2);

		List<ParameterBase<FromToType<Calendar>>> list = new ArrayList<>();
		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(FromToType.<Calendar>builder().from(c1).to(null).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Calendar>builder().from(null).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Calendar>builder().from(c1).to(c1).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Calendar>builder().from(c1).to(c2).build(), 0));
		list.add(new ParameterBase<>(FromToType.<Calendar>builder().from(c2).to(c1).build(), 1));

		return list;
	}

	public FromToByCalendarTest(ParameterBase<FromToType<Calendar>> parameter) {

		super(parameter);
	}
}
