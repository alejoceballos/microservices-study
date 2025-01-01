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
import lombok.RequiredArgsConstructor;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(
        name = "Loans API",
        description = "Loans operations"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/loans", produces = {APPLICATION_JSON_VALUE})
@Validated
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
            @NotBlank(message = "Mobile Number can not be a null or empty")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam final String mobileNumber
    ) {
        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto<>(
                        "Loan created",
                        loanService.create(mobileNumber)));
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
    public ResponseEntity<ResponseDto<LoanDto>> findByMobileNumber(
            @NotBlank(message = "Mobile Number can not be a null or empty")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
            @RequestParam final String mobileNumber
    ) {
        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Loan retrieved",
                        loanService.findByMobileNumber(mobileNumber)));
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
        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Loan updated",
                        loanService.update(loan)));
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
            @NotBlank(message = "Loan number can not be a null or empty")
            @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits")
            @RequestParam final String loanNumber
    ) {
        loanService.deleteByLoanNumber(loanNumber);

        return ResponseEntity
                .status(OK)
                .body("Loan deleted");
    }

}
