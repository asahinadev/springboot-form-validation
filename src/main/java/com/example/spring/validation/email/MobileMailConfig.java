package com.example.spring.validation.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("com.example.spring.validation.email")
public class MobileMailConfig {

	String[] allows;

	String[] denied;

}
