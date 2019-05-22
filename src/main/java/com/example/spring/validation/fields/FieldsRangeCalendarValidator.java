package com.example.spring.validation.fields;

import java.util.Calendar;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeCalendarValidator
		extends FieldsRangeComparableValidator<Calendar> {

	@Override
	Logger log() {
		return log;
	}

}
