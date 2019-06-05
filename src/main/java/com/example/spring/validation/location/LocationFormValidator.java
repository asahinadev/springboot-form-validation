package com.example.spring.validation.location;

import javax.validation.ConstraintValidatorContext;

import com.example.spring.form.LocationType;
import com.example.spring.validation.BasedValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocationFormValidator
		extends BasedValidator<Location, LocationType> {

	Location annotation;

	@Override
	public void initialize(Location annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(LocationType form, ConstraintValidatorContext context) {

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
				notNullValidator(fieldLat, lat, annotation.notNullLat(), context);
				notNullValidator(fieldLng, lng, annotation.notNullLng(), context);

				// 最小値チェック
				minValidator(fieldLat, lat, annotation.minLat(), context);
				minValidator(fieldLng, lng, annotation.minLng(), context);

				// 最大値チェック
				maxValidator(fieldLat, lat, annotation.maxLat(), context);
				maxValidator(fieldLng, lng, annotation.maxLng(), context);

			} while (false);
		} catch (RuntimeException e) {
			log.debug(e.getMessage(), e);
			return false;
		}

		return true;
	}
}
