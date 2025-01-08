package com.momo2x.momobank.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "build")
public class BuildDto {

    private String name;
    private String version;
    private String profile;

}
