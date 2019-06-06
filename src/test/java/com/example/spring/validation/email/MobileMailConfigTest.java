package com.example.spring.validation.email;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileMailConfigTest {

	@Autowired
	MobileMailConfig config;

	@Test
	public void test() {

		log.debug("{}", Arrays.toString(config.getAllows()));
		log.debug("{}", Arrays.toString(config.getDenied()));
	}

}
