package com.momo2x.momobank.accounts.controller;

import com.momo2x.momobank.accounts.dto.AccountDto;
import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.dto.ErrorResponseDto;
import com.momo2x.momobank.accounts.dto.ResponseDto;
import com.momo2x.momobank.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.momo2x.momobank.accounts.constant.AccountsConstants.Account.NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Account.NUMBER_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Account.NUMBER_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Account.NUMBER_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.accounts.constant.AccountsConstants.Customer.MOBILE_PATTERN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Tag(name = "Accounts API", description = "Customers and Accounts operations")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/v1", produces = {APPLICATION_JSON_VALUE})
public class AccountsController {

    private final AccountService accountService;

    @Operation(
            summary = "Create an account",
            description = "Creates a new customer along its account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "210",
                    description = "CREATED",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ResponseDto<AccountDto>> create(
            @Valid
            @RequestBody final CustomerDto customer
    ) {
        final var created = accountService.create(customer);

        log.debug("Created {}", created);

        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto<>(
                        "Account created",
                        created));
    }

    @Operation(
            summary = "Find account by mobile number",
            description = "Finds the account using the customer's mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<ResponseDto<AccountDto>> findByMobileNumber(
            @Schema(description = "Customer's mobile number", example = "+999836000801")
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var account = accountService.findByCustomerMobileNumber(mobileNumber);

        log.debug("Found {}", account);

        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Account retrieved",
                        account));
    }

    @Operation(
            summary = "Update an account",
            description = "Updates an existing customer along its account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<ResponseDto<AccountDto>> update(
            @Valid
            @RequestBody final AccountDto account
    ) {
        final var updated = accountService.update(account);

        log.debug("Updated {}", updated);

        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Account updated",
                        updated));
    }

    @Operation(
            summary = "Delete account by mobile number",
            description = "Deletes an account according to  the customer's mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> delete(
            @Schema(
                    description = "The unique account number",
                    example = "1901000003"
            )
            @NotBlank(message = NUMBER_IS_MANDATORY)
            @NotNull(message = NUMBER_IS_MANDATORY)
            @Min(value = NUMBER_MIN, message = NUMBER_RANGE)
            @Max(value = NUMBER_MAX, message = NUMBER_RANGE)
            @PathVariable Long accountNumber
    ) {
        accountService.deleteByAccountNumber(accountNumber);

        return ResponseEntity
                .status(OK)
                .body("Account deleted");
    }

}
