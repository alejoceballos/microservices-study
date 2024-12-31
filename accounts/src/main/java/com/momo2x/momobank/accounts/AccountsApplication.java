package com.momo2x.momobank.accounts;

import com.momo2x.momobank.accounts.audit.AuditorWrapper;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.run;

@OpenAPIDefinition(
		info = @Info(
				title = "Accounts API",
				description = "Accounts Rest API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Alejo Ceballos",
					    url = "https://github.com/alejoceballos"
				),
				license = @License(
						name = "MIT",
						url = "https://opensource.org/license/mit"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts API Github API project",
				url = "https://github.com/alejoceballos/microservices-study"
		)
)
@EnableJpaAuditing(auditorAwareRef = AuditorWrapper.BEAN_NAME)
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		run(AccountsApplication.class, args);
	}

}
