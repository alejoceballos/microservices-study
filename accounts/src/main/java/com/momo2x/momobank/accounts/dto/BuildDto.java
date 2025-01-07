package com.momo2x.momobank.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
public record BuildDto(String name, String version, String profile) {
}
