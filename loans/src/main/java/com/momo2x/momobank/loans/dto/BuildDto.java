package com.momo2x.momobank.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "loans")
public class BuildDto {

    private String name;
    private String version;
    private String profile;

}
