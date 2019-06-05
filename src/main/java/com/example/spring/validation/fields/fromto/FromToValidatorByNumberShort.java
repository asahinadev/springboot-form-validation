package com.example.spring.validation.fields.fromto;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByNumberShort
		extends FromToFormValidator<Short> {

	@Override
	Logger log() {
		return log;
	}

}
