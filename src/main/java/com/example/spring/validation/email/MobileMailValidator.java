package com.example.spring.validation.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;
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

		boolean flag = true;

		do {
			log.debug("configure （denied)");
			flag = match(value, mobileMailConfig.denied, false);
			if (!flag) {
				break;
			}

			log.debug("annotation（denied)");
			flag = match(value, annotation.denied(), false);
			if (!flag) {
				break;
			}

			log.debug("configure （allows)");
			flag = match(value, mobileMailConfig.allows, true);
			if (!flag) {
				break;
			}

			log.debug("annotation（allows)");
			flag = match(value, annotation.allows(), true);

		} while (false);
		return flag;

	}

	protected boolean match(String target, String[] domains, boolean match) {

		if (ArrayUtils.isEmpty(domains)) {
			// 対象が空の場合は true
			return true;
		}
		target = StringUtils.defaultString(target);

		List<String> targetDomains = new ArrayList<>();
		int start = 0;
		while (start >= 0) {
			start = target.indexOf('.', start + 1);
			if (start > 0) {
				targetDomains.add(target.substring(start + 1));
			}
		}

		log.debug("tagetDomains {}", targetDomains);

		for (String domain : domains) {
			String test1 = target.split("@", 2)[1];
			log.debug("{ target: {}, domain {} }", test1, domain);

			if (Objects.equals(test1, domain)) {
				log.debug("完全一致(" + match + ")");
				return match;
			}

			if (targetDomains.contains(domain)) {
				log.debug("部分一致(" + match + ")");
				return match;
			}
		}
		log.debug("不一致(" + (!match) + ")");
		return !match;
	}

}
