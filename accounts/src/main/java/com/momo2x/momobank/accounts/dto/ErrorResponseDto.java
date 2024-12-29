package com.momo2x.momobank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private LocalDateTime errorTime;
    private String apiPath;
    private final int httpCode;
    private final String httpSeries;
    private final String httpDescription;
    private String errorMessage;

}
