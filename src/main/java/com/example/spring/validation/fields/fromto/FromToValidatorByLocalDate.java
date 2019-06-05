package com.example.spring.validation.fields.fromto;

import java.time.LocalDate;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByLocalDate
		extends FromToFormValidator<LocalDate> {

	@Override
	Logger log() {

		return log;
	}

}
