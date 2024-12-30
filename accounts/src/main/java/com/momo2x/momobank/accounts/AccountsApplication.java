package com.momo2x.momobank.accounts;

import com.momo2x.momobank.accounts.audit.AuditorWrapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.run;

@EnableJpaAuditing(auditorAwareRef = AuditorWrapper.BEAN_NAME)
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		run(AccountsApplication.class, args);
	}

}
