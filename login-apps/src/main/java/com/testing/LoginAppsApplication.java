package com.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.testing.config.AppProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class LoginAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginAppsApplication.class, args);
	}

}
