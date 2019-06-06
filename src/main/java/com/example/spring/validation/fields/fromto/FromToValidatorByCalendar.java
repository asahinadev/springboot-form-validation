package com.example.spring.validation.fields.fromto;

import java.util.Calendar;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FromToValidatorByCalendar
		extends FromToFormValidator<Calendar> {

	@Override
	Logger log() {

		return log;
	}

}
