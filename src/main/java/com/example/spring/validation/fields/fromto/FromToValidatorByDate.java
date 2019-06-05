package com.example.spring.validation.fields.fromto;

import java.util.Date;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByDate
		extends FromToFormValidator<Date> {

	@Override
	Logger log() {
		return log;
	}

}
