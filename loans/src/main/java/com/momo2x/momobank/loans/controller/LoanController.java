package com.momo2x.momobank.loans.controller;

import com.momo2x.momobank.loans.dto.ErrorResponseDto;
import com.momo2x.momobank.loans.dto.LoanDto;
import com.momo2x.momobank.loans.dto.ResponseDto;
import com.momo2x.momobank.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Customer.MOBILE_PATTERN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_IS_INVALID;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_LENGTH_RANGE;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_MAX;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_MIN;
import static com.momo2x.momobank.loans.constants.LoansConstants.Loan.LOAN_NUMBER_PATTERN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Loans API", description = "Loans operations")
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/v1", produces = {APPLICATION_JSON_VALUE})
public class LoanController {

    private final LoanService loanService;

    @Operation(
            summary = "Create a loan",
            description = "Creates a new loan"
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
    public ResponseEntity<ResponseDto<LoanDto>> create(
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var  created = loanService.create(mobileNumber);

        log.debug("Created {}", created);

        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto<>(
                        "Loan created",
                        created));
    }

    @Operation(
            summary = "Find loan by mobile number",
            description = "Finds the loan using the customer's mobile number"
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
    public ResponseEntity<LoanDto> findByMobileNumber(
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var loan = loanService.findByMobileNumber(mobileNumber);

        log.debug("Found {}", loan);

        return ResponseEntity
                .status(OK)
                .body(loan);
    }

    @Operation(
            summary = "Update a loan info",
            description = "Updates an existing loan information"
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
    public ResponseEntity<ResponseDto<LoanDto>> update(
            @Valid
            @RequestBody final LoanDto loan
    ) {
        final var updated = loanService.update(loan);

        log.debug("Updated {}", updated);

        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Loan updated",
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
    @DeleteMapping("/{loanNumber}")
    public ResponseEntity<String> delete(
            @NotBlank(message = LOAN_NUMBER_IS_MANDATORY)
            @Size(min = LOAN_NUMBER_MIN, max = LOAN_NUMBER_MAX, message = LOAN_NUMBER_LENGTH_RANGE)
            @Pattern(regexp = LOAN_NUMBER_PATTERN, message = LOAN_NUMBER_IS_INVALID)
            @RequestParam final String loanNumber
    ) {
        loanService.deleteByLoanNumber(loanNumber);

        return ResponseEntity
                .status(OK)
                .body("Loan deleted");
    }

}
