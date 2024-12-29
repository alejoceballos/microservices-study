package com.momo2x.momobank.accounts.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private CustomerDto customer;
}
