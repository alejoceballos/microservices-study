package com.momo2x.momobank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Account information. May also include customer's info"
)
@Data
@Builder
public class AccountDto {

    public static final String NUMBER_IS_MANDATORY = "Account number is mandatory";
    public static final String NUMBER_RANGE = "Account number range is between 1 million and 1.9 millions";
    public static final long NUMBER_MIN = 1_000_000_000;
    public static final long NUMBER_MAX = 1_900_000_000;

    public static final String TYPE_IS_MANDATORY = "Account type is mandatory";
    public static final String TYPE_LENGTH_RANGE = "Account type length range is between 1 and 100";
    public static final int TYPE_MIN = 1;
    public static final int TYPE_MAX = 100;

    public static final String BRANCH_ADDR_IS_MANDATORY = "Branch address is mandatory";
    public static final String BRANCH_ADDR_LENGTH_RANGE = "Branch address length range is between 1 and 200";
    public static final int BRANCH_ADDR_MIN = 1;
    public static final int BRANCH_ADDR_MAX = 200;

    @Schema(
            description = "The unique account number for the customer",
            example = "1901000003"
    )
    @NotNull(message = NUMBER_IS_MANDATORY)
    @Min(value = NUMBER_MIN, message = NUMBER_RANGE)
    @Max(value = NUMBER_MAX, message = NUMBER_RANGE)
    private Long accountNumber;

    @Schema(
            description = "The account type, like \"savings\", or \"salary\", etc.",
            example = "SAVINGS"
    )
    @NotBlank(message = TYPE_IS_MANDATORY)
    @Size(min = TYPE_MIN, max = TYPE_MAX, message = TYPE_LENGTH_RANGE)
    private String accountType;

    @Schema(
            description = "Branch address",
            example = "9590 Nowhere Blvd, Utopia City, NW 91730, In The Clouds"
    )
    @NotBlank(message = BRANCH_ADDR_IS_MANDATORY)
    @Size(min = BRANCH_ADDR_MIN, max = BRANCH_ADDR_MAX, message = BRANCH_ADDR_LENGTH_RANGE)
    private String branchAddress;

    private CustomerDto customer;
}
