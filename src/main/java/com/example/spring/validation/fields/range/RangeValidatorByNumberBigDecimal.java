package com.example.spring.validation.fields.range;

import java.math.BigDecimal;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByNumberBigDecimal
		extends RangeFormValidator<BigDecimal> {

	@Override
	Logger log() {
		return log;
	}

}