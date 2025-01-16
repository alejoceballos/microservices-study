package com.momo2x.momobank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.BRANCH_ADDR_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.BRANCH_ADDR_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.BRANCH_ADDR_MAX;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.BRANCH_ADDR_MIN;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.NUMBER_MAX;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.NUMBER_MIN;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.NUMBER_RANGE;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.TYPE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.TYPE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.TYPE_MAX;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Account.TYPE_MIN;

@Schema(name = "Account", description = "Account information. May also include customer's info")
@Data
@Builder
public class AccountDto {

    @Schema(description = "The unique account number for the customer", example = "1901000003")
    @NotNull(message = NUMBER_IS_MANDATORY)
    @Min(value = NUMBER_MIN, message = NUMBER_RANGE)
    @Max(value = NUMBER_MAX, message = NUMBER_RANGE)
    private Long accountNumber;

    @Schema(description = "The account type, like \"savings\", or \"salary\", etc.", example = "SAVINGS")
    @NotBlank(message = TYPE_IS_MANDATORY)
    @Size(min = TYPE_MIN, max = TYPE_MAX, message = TYPE_LENGTH_RANGE)
    private String accountType;

    @Schema(description = "Branch address", example = "9590 Nowhere Blvd, Utopia City, NW 91730, In The Clouds")
    @NotBlank(message = BRANCH_ADDR_IS_MANDATORY)
    @Size(min = BRANCH_ADDR_MIN, max = BRANCH_ADDR_MAX, message = BRANCH_ADDR_LENGTH_RANGE)
    private String branchAddress;

    private CustomerDto customer;
}
