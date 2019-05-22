package com.example.spring.validation.fields;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeLocalDateTimeValidator
		extends FieldsRangeComparatorValidator<LocalDateTime> {
	FieldsRange annotation;

	@Override
	public int compare(LocalDateTime from, LocalDateTime to) {
		return new CompareToBuilder()
				.append(from.getYear(), to.getYear())
				.append(from.getMonthValue(), to.getMonthValue())
				.append(from.getDayOfMonth(), to.getDayOfMonth())
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
