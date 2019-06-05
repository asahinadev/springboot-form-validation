package com.example.spring.validation.location;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.spring.form.LatLng;

public class LocationPojoValidator implements ConstraintValidator<Location, Object> {

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

		BeanWrapper form = new BeanWrapperImpl(value);
		LocationFormValidator validator = new LocationFormValidator();
		validator.initialize(annotation);

		return validator.isValid(new LatLng(
				(Double) form.getPropertyValue(annotation.fieldLat()),
				(Double) form.getPropertyValue(annotation.fieldLng())), context);
	}

}
