package com.example.spring.validation.fields.fromto;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByNumberInteger
		extends FromToFormValidator<Integer> {

	@Override
	Logger log() {
		return log;
	}

}
