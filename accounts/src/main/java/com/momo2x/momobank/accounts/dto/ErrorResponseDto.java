package com.momo2x.momobank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private LocalDateTime errorTime;
    private String apiPath;
    private List<String> errorMessages;

}
