package com.momo2x.momobank.accounts.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;

}
