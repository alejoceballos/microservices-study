package com.momo2x.momobank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.EMAIL_IS_INVALID;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.EMAIL_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.EMAIL_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.EMAIL_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.EMAIL_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_PATTERN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.NAME_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.NAME_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.NAME_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.NAME_MIN;

@Schema(name = "CustomerDetails", description = "Schema to hold Customer, Account, Cards and Loans information")
@Data
@Builder
public class CustomerDetailsDto {

    @Schema(description = "Customer's full name", example = "John Doe da Silva")
    @NotBlank(message = NAME_IS_MANDATORY)
    @Size(min = NAME_MIN, max = NAME_MAX, message = NAME_LENGTH_RANGE)
    private String name;

    @Schema(description = "Customer's personal email", example = "john.doe@utopia.com")
    @NotBlank(message = EMAIL_IS_MANDATORY)
    @Size(min = EMAIL_MIN, max = EMAIL_MAX, message = EMAIL_LENGTH_RANGE)
    @Email(message = EMAIL_IS_INVALID)
    private String email;

    @Schema(description = "Customer's main mobile number", example = "+999836000801")
    @NotBlank(message = MOBILE_IS_MANDATORY)
    @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
    @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountDto accountDto;

    @Schema(description = "Loans details of the Customer")
    private LoanDto loanDto;

    @Schema(description = "Cards details of the Customer")
    private CardDto cardDto;

}