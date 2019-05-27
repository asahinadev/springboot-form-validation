package com.example.spring.validation.fields.range;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeDoubleValidator
		extends FieldsRangeComparableValidator<Double> {

	@Override
	Logger log() {
		return log;
	}

}
