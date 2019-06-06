package com.example.spring.validation.email;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileMailValidator
		implements ConstraintValidator<MobileMail, String> {

	MobileMail annotation;

	@Autowired
	MobileMailConfig mobileMailConfig;

	@Override
	public void initialize(MobileMail annotation) {

		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(value)) {
			return true;
		}

		if (!StringUtils.contains(value, "@")) {
			return true;
		}

		boolean flag = true;

		log.debug("configure （denied)");
		flag = flag && match(value, mobileMailConfig.denied, false);
		log.debug("annotation（denied)");
		flag = flag && match(value, annotation.denied(), false);

		log.debug("configure （allows)");
		flag = flag && match(value, mobileMailConfig.allows, true);
		log.debug("annotation（allows)");
		flag = flag && match(value, annotation.allows(), true);

		return flag;

	}

	protected boolean match(String target, String[] domains, boolean match) {

		if (domains == null || domains.length == 0) {
			// 対象が空の場合は true
			return true;
		}

		for (String domain : domains) {
			String test1 = target.split("@", 2)[1];
			log.debug("{ target: {}, domain {} }", test1, domain);

			if (Objects.equals(test1, domain)) {
				log.debug("完全一致(" + match + ")");
				return match;
			}

			String test2 = "";
			do {
				test2 = test1.split("\\.", 2)[1];
				log.debug("{ target: {}, domain {} }", test2, domain);

				if (Objects.equals(test2, domain)) {
					log.debug("部分一致(" + match + ")");
					return match;
				}
			} while (test2.matches("\\."));
		}
		log.debug("不一致(" + (!match) + ")");
		return !match;
	}

}
