package com.example.spring.validation.location;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;

import com.example.spring.form.LocationType;
import com.example.spring.validation.BasedValidator;

public class LocationPojoValidator
		extends BasedValidator<Location, Object> {

	Location annotation;

	@Override
	public void initialize(Location annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		BeanWrapper form = form(value);
		LocationFormValidator validator = new LocationFormValidator();
		validator.initialize(annotation);

		return validator.isValid(
				new LocationType(
						property(form, annotation.fieldLat()),
						property(form, annotation.fieldLng())),
				context);
	}

}
