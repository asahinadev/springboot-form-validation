package com.example.spring.validation.fields.range;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeFloatValidator
		extends FieldsRangeComparableValidator<Float> {

	@Override
	Logger log() {
		return log;
	}

}
