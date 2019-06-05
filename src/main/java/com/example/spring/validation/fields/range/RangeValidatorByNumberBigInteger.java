package com.example.spring.validation.fields.range;

import java.math.BigInteger;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByNumberBigInteger
		extends RangeFormValidator<BigInteger> {

	@Override
	Logger log() {
		return log;
	}

}
