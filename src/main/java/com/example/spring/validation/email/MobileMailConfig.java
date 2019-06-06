package com.example.spring.validation.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("com.example.spring.validation.email")
public class MobileMailConfig {

	String[] allows;

	String[] denied;

}
