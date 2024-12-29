package com.momo2x.momobank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto<T> {

    private String message;
    private T data;

}
