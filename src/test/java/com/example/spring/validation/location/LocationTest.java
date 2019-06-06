package com.example.spring.validation.location;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import com.example.spring.SpringParameterized;
import com.example.spring.form.LocationType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ActiveProfiles("test")
@ComponentScan(basePackages = {
		"com.example.spring"
})
@RunWith(Parameterized.class)
@SpringBootTest
public class LocationTest
		extends SpringParameterized<LocationTest.Form, LocationType> {

	@Parameterized.Parameters
	public static List<ParameterBase<LocationType>> data() {

		Double latMin = Double.valueOf(Location.JP_LAT_MIN);
		Double latMax = Double.valueOf(Location.JP_LAT_MAX);

		Double lngMin = Double.valueOf(Location.JP_LNG_MIN);
		Double lngMax = Double.valueOf(Location.JP_LNG_MAX);

		List<ParameterBase<LocationType>> list = new ArrayList<>();

		list.add(new ParameterBase<>(null, 0));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(null).lng(lngMax).build(), 1));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMax).lng(null).build(), 1));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(null).lng(null).build(), 0));

		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMin).lng(lngMin).build(), 0));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMin).lng(lngMax).build(), 0));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMax).lng(lngMin).build(), 0));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMax).lng(lngMax).build(), 0));

		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMin).lng(lngMin - 1D).build(), 1));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMin - 1D).lng(lngMax).build(), 1));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMax + 1D).lng(lngMin).build(), 1));
		list.add(new ParameterBase<>(
				LocationType.builder().lat(latMax).lng(lngMax + 1D).build(), 1));

		return list;
	}

	public LocationTest(ParameterBase<LocationType> parameter) {

		super(parameter);
	}

	@Getter
	@RequiredArgsConstructor
	public static class Form {

		@Location
		final LocationType value;
	}

	@Override
	protected Form createForm() {

		return new Form(parameter.value);
	}

}
