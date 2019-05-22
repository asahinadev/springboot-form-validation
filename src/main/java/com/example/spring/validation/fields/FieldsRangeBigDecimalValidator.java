package com.example.spring.validation.fields;

import java.math.BigDecimal;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeBigDecimalValidator
		extends FieldsRangeComparableValidator<BigDecimal> {

	@Override
	Logger log() {
		return log;
	}

}
