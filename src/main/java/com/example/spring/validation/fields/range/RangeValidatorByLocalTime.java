package com.example.spring.validation.fields.range;

import java.time.LocalTime;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByLocalTime
		extends RangeFormValidator<LocalTime> {

	Range annotation;

	@Override
	Logger log() {

		return log;
	}

}
