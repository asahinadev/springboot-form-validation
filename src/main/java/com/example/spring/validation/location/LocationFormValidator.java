package com.example.spring.validation.location;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForDouble;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MinValidatorForDouble;

import com.example.spring.form.LatLng;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocationFormValidator implements ConstraintValidator<Location, LatLng> {

	Location annotation;

	@Override
	public void initialize(Location annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(LatLng form, ConstraintValidatorContext context) {

		if (form == null) {
			return true;
		}

		Double lat = form.getLat();
		Double lng = form.getLng();

		String fieldLat = annotation.fieldLat();
		String fieldLng = annotation.fieldLng();

		try {
			do {

				if (lat == null && lng == null) {
					log.debug("lat and lng is null");
					return true;
				}

				// 必須チェック
				notEmptyValidator(fieldLat, lat, annotation.notNullLat(), context);
				notEmptyValidator(fieldLng, lng, annotation.notNullLng(), context);

				// 最小値チェック
				minValidator(fieldLat, lat, annotation.minLat(), context);
				minValidator(fieldLng, lng, annotation.minLng(), context);

				// 最大値チェック
				maxValidator(fieldLat, lat, annotation.maxLat(), context);
				maxValidator(fieldLng, lng, annotation.maxLng(), context);

			} while (false);
		} catch (RuntimeException e) {
			return false;
		}

		return true;
	}

	void notEmptyValidator(String field, Object value, NotNull notNull, ConstraintValidatorContext context) {

		NotNullValidator validator = new NotNullValidator();
		validator.initialize(notNull);

		if (!validator.isValid(value, context)) {
			error(field, context, notNull.message());
			throw new RuntimeException(field);
		}
	}

	void minValidator(String field, Double value, Min min, ConstraintValidatorContext context) {

		MinValidatorForDouble validator = new MinValidatorForDouble();
		validator.initialize(min);

		if (!validator.isValid(value, context)) {
			error(field, context, min.message());
			throw new RuntimeException(field);
		}
	}

	void maxValidator(String field, Double value, Max max, ConstraintValidatorContext context) {

		MaxValidatorForDouble validator = new MaxValidatorForDouble();
		validator.initialize(max);

		if (!validator.isValid(value, context)) {
			error(field, context, max.message());
			throw new RuntimeException(field);
		}
	}

	boolean error(String field, ConstraintValidatorContext context, String message) {

		context.disableDefaultConstraintViolation();
		ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(message);
		if (field != null) {
			builder.addPropertyNode(field).addConstraintViolation();
		} else {
			builder.addConstraintViolation();
		}
		return false;
	}
}
