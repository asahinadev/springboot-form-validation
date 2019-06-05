package com.example.spring.validation.fields.range;

import java.time.LocalDate;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByLocalDate
		extends RangeFormValidator<LocalDate> {

	Range annotation;

	@Override
	Logger log() {

		return log;
	}

}
