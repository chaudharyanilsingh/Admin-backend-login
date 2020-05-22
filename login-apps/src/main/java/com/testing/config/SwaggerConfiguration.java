package com.testing.config;
import java.util.ArrayList;
import java.util.List;import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.collect.Lists;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket swaggerSpringfoxDocket() {

		Contact contact = new Contact("anil chaudhary", null, null);

		List<VendorExtension> vext = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo("Backend API", "This is the pratice for login through social media", "1.0.0",
				null, contact, "jaipur", "https://localhost:8080", vext);

		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).pathMapping("/")
				.forCodeGeneration(true).securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey())).useDefaultResponseMessages(false);

		docket = docket.select().build();
		return docket;
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}