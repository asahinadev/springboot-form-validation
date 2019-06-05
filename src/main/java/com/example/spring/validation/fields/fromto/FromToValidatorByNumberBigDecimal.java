package com.example.spring.validation.fields.fromto;

import java.math.BigDecimal;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByNumberBigDecimal
		extends FromToFormValidator<BigDecimal> {

	@Override
	Logger log() {
		return log;
	}

}
