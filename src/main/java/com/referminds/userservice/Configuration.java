package com.referminds.userservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("user-service")
@Data
public class Configuration {
	private Integer minExp;

}
