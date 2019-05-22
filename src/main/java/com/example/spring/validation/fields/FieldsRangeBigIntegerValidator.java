package com.example.spring.validation.fields;

import java.math.BigInteger;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeBigIntegerValidator
		extends FieldsRangeComparableValidator<BigInteger> {

	@Override
	Logger log() {
		return log;
	}

}
