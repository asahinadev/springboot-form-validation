package com.example.spring.validation.fields.fromto;

import java.math.BigInteger;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByNumberBigInteger
		extends FromToFormValidator<BigInteger> {

	@Override
	Logger log() {
		return log;
	}

}
