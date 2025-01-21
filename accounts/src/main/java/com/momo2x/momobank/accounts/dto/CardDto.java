package com.momo2x.momobank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_AMOUNT_USED_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_AVAILABLE_AMOUNT_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_LIMIT_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_IS_INVALID;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_NUMBER_PATTERN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_TYPE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_TYPE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_TYPE_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Card.CARD_TYPE_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_PATTERN;

@Schema(name = "Cards", description = "Schema to hold Card information")
@Data
public class CardDto {

    @Schema(description = "Mobile Number of Customer", example = "4354437687")
    @NotBlank(message = MOBILE_IS_MANDATORY)
    @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
    @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
    private String mobileNumber;

    @Schema(description = "Card Number of the customer", example = "100646930341")
    @NotBlank(message = CARD_NUMBER_IS_MANDATORY)
    @Size(min = CARD_NUMBER_MIN, max = CARD_NUMBER_MAX, message = CARD_NUMBER_LENGTH_RANGE)
    @Pattern(regexp = CARD_NUMBER_PATTERN, message = CARD_NUMBER_IS_INVALID)
    private String cardNumber;

    @Schema(description = "Type of the card", example = "Credit Card")
    @NotBlank(message = CARD_TYPE_IS_MANDATORY)
    @Size(min = CARD_TYPE_MIN, max = CARD_TYPE_MAX, message = CARD_TYPE_LENGTH_RANGE)
    private String cardType;

    @Schema(description = "Total amount limit available against a card", example = "100000")
    @Positive(message = CARD_LIMIT_RANGE)
    private int totalLimit;

    @Schema(description = "Total amount used by a Customer", example = "1000")
    @PositiveOrZero(message = CARD_AMOUNT_USED_RANGE)
    private int amountUsed;

    @Schema(description = "Total available amount against a card", example = "90000")
    @PositiveOrZero(message = CARD_AVAILABLE_AMOUNT_RANGE)
    private int availableAmount;

}