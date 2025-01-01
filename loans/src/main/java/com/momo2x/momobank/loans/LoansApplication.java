package com.momo2x.momobank.loans;

import com.momo2x.momobank.loans.audit.LoansAuditor;
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
				title = "Loans API",
				description = "Loans Rest API Documentation",
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
				description = "Loans API Github API project",
				url = "https://github.com/alejoceballos/microservices-study"
		)
)
@EnableJpaAuditing(auditorAwareRef = LoansAuditor.BEAN_NAME)
@SpringBootApplication
public class LoansApplication {

	public static void main(String[] args) {
		run(LoansApplication.class, args);
	}

}
