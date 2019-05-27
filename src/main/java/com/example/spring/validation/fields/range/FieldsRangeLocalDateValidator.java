package com.example.spring.validation.fields.range;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldsRangeLocalDateValidator
		extends FieldsRangeComparatorValidator<LocalDate> {
	FieldsRange annotation;

	@Override
	public int compare(LocalDate from, LocalDate to) {
		return new CompareToBuilder()
				.append(from.getYear(), to.getYear())
				.append(from.getMonthValue(), to.getMonthValue())
				.append(from.getDayOfMonth(), to.getDayOfMonth())
				.build();
	}

	@Override
	Logger log() {
		return log;
	}

}
