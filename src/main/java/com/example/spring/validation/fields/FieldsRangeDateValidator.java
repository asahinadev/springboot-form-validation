package com.example.spring.validation.fields;

import java.util.Date;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeDateValidator
		extends FieldsRangeComparableValidator<Date> {

	@Override
	Logger log() {
		return log;
	}

}
