package com.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.testing.config.AppProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
@EnableSwagger2
public class LoginAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginAppsApplication.class, args);
	}

}
