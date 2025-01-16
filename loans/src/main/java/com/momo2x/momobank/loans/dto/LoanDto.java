package com.momo2x.momobank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_PATTERN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_AMOUNT_USED_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_AVAILABLE_AMOUNT_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_LIMIT_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_IS_INVALID;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_LENGTH_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_MAX;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_MIN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_PATTERN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_TYPE_IS_MANDATORY;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_TYPE_LENGTH_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_TYPE_MAX;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_TYPE_MIN;

@Schema(name = "Loans", description = "Loan information")
@Data
@Builder
public class LoanDto {

    @Schema(description = "Mobile Number of Customer", example = "4354437687")
    @NotBlank(message = MOBILE_IS_MANDATORY)
    @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
    @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
    private String mobileNumber;

    @Schema(description = "Loan Number of the customer", example = "548732457654")
    @NotBlank(message = LOAN_NUMBER_IS_MANDATORY)
    @Size(min = LOAN_NUMBER_MIN, max = LOAN_NUMBER_MAX, message = LOAN_NUMBER_LENGTH_RANGE)
    @Pattern(regexp = LOAN_NUMBER_PATTERN, message = LOAN_NUMBER_IS_INVALID)
    private String loanNumber;

    @Schema(description = "Type of the loan", example = "Home Loan")
    @NotBlank(message = LOAN_TYPE_IS_MANDATORY)
    @Size(min = LOAN_TYPE_MIN, max = LOAN_TYPE_MAX, message = LOAN_TYPE_LENGTH_RANGE)
    private String loanType;

    @Schema(description = "Total loan amount", example = "100000")
    @Positive(message = LOAN_LIMIT_RANGE)
    private int totalLoan;

    @Schema(description = "Total loan amount paid", example = "1000")
    @PositiveOrZero(message = LOAN_AMOUNT_USED_RANGE)
    private int amountPaid;

    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    @PositiveOrZero(message = LOAN_AVAILABLE_AMOUNT_RANGE)
    private int outstandingAmount;

}
