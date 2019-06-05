package com.example.spring.validation.fields.range;

import java.util.Calendar;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByCalendar
		extends RangeFormValidator<Calendar> {

	@Override
	Logger log() {
		return log;
	}

}
