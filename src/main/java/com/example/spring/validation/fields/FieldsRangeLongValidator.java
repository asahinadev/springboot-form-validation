package com.example.spring.validation.fields;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeLongValidator
		extends FieldsRangeComparableValidator<Long> {

	@Override
	Logger log() {
		return log;
	}

}
