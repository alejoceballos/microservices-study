package com.momo2x.momobank.loans.config;

import com.momo2x.momobank.loans.dto.BuildDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoansConfigProperties {

    @Bean
    @ConfigurationProperties(prefix = "build")
    public BuildDto buildInfo() {
        return new BuildDto();
    }

}
