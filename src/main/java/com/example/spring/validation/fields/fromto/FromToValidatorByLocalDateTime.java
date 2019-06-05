package com.example.spring.validation.fields.fromto;

import java.time.LocalDateTime;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByLocalDateTime
		extends FromToFormValidator<LocalDateTime> {

	@Override
	Logger log() {

		return log;
	}

}
