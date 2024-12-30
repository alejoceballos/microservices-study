package com.momo2x.momobank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    public static final String NUMBER_IS_MANDATORY = "Account number is mandatory";
    public static final String TYPE_IS_MANDATORY = "Account type is mandatory";
    public static final String TYPE_MAX_IS_100 = "Account type maximum length is 100";
    public static final int TYPE_MAX = 100;
    public static final String BRANCH_ADDR_IS_MANDATORY = "Branch address is mandatory";
    public static final String BRANCH_ADDR_TYPE_MAX_IS_200 = "Branch address maximum length is 200";
    public static final int BRANCH_ADDR_MAX = 200;

    @NotNull(message = NUMBER_IS_MANDATORY)
    private Long accountNumber;

    @NotBlank(message = TYPE_IS_MANDATORY)
    @Size(max = TYPE_MAX, message = TYPE_MAX_IS_100)
    private String accountType;

    @NotBlank(message = BRANCH_ADDR_IS_MANDATORY)
    @Size(max = BRANCH_ADDR_MAX, message = BRANCH_ADDR_TYPE_MAX_IS_200)
    private String branchAddress;

    private CustomerDto customer;
}
