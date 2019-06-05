package com.example.spring.validation.fields.fromto;

import java.time.LocalTime;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByLocalTime
		extends FromToFormValidator<LocalTime> {

	@Override
	Logger log() {

		return log;
	}

}
