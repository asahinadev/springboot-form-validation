package com.example.spring.validation.fields;

import java.time.LocalTime;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeLocalTimeValidator
		extends FieldsRangeComparatorValidator<LocalTime> {
	FieldsRange annotation;

	@Override
	public int compare(LocalTime from, LocalTime to) {
		return new CompareToBuilder()
				.append(from.getHour(), to.getHour())
				.append(from.getMinute(), to.getMinute())
				.append(from.getSecond(), to.getSecond())
				.append(from.getNano(), to.getNano())
				.build();
	}

	@Override
	Logger log() {
		return log;
	}

}
