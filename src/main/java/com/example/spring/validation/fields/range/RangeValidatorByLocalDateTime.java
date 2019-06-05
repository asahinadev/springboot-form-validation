package com.example.spring.validation.fields.range;

import java.time.LocalDateTime;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangeValidatorByLocalDateTime
		extends RangeFormValidator<LocalDateTime> {

	Range annotation;

	@Override
	Logger log() {

		return log;
	}

}
