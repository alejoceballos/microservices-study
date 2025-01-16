package com.momo2x.momobank.accounts.controller;

import com.momo2x.momobank.accounts.dto.CustomerDetailsDto;
import com.momo2x.momobank.accounts.dto.ErrorResponseDto;
import com.momo2x.momobank.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.accounts.constants.AccountsConstants.Customer.MOBILE_PATTERN;

@Tag(name = "REST API for Customers in EazyBank", description = "REST APIs in EazyBank to FETCH customer details")
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/v1/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsCustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping()
    public ResponseEntity<CustomerDetailsDto> findByMobileNumber(
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var details = customerService.findCustomerDetailsByMobileNumber(mobileNumber);

        log.debug("Found {}", details);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(details);
    }


}