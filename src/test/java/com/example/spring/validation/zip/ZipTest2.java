package com.example.spring.validation.zip;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class ZipTest2
		extends SpringParameterized<ZipTest2.Form, ZipTest2.ZipType> {

	@Parameterized.Parameters
	public static List<ParameterBase<ZipTest2.ZipType>> data() {

		List<ParameterBase<ZipTest2.ZipType>> list = new ArrayList<>();

		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("").zip2("").build(), 0));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("").zip2("1").build(), 1));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("1").zip2("").build(), 1));

		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("A").zip2("1").build(), 1));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("1").zip2("A").build(), 1));

		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("12").zip2("1234").build(), 1));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("1234").zip2("1234").build(), 1));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("123").zip2("123").build(), 1));
		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("123").zip2("12345").build(), 1));

		list.add(new ParameterBase<>(
				ZipTest2.ZipType.builder().zip1("123").zip2("1234").build(), 0));

		return list;
	}

	public ZipTest2(ParameterBase<ZipTest2.ZipType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@Zip
		final ZipTest2.ZipType value;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ZipType {

		String zip1;

		String zip2;

	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
