package com.example.spring.validation.fields.range;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByNumberDouble
		extends RangeFormValidator<Double> {

	@Override
	Logger log() {
		return log;
	}

}
