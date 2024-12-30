package com.momo2x.momobank.accounts.controller;

import com.momo2x.momobank.accounts.dto.AccountDto;
import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.dto.ResponseDto;
import com.momo2x.momobank.accounts.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto<AccountDto>> create(
            @Valid @RequestBody final CustomerDto customer
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(
                        "Account created",
                        accountService.create(customer)));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<AccountDto>> findByMobileNumber(
            @NotBlank(message = CustomerDto.MOBILE_IS_MANDATORY)
            @Pattern(regexp = CustomerDto.MOBILE_PATTERN, message = CustomerDto.MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        "Account retrieved",
                        accountService.findByCustomerMobileNumber(mobileNumber)));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<AccountDto>> update(
            @Valid
            @RequestBody final AccountDto account
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(
                        "Account updated",
                        accountService.update(account)));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> delete(
            @NotBlank(message = AccountDto.NUMBER_IS_MANDATORY)
            @PathVariable Long accountNumber
    ) {
        accountService.deleteByAccountNumber(accountNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Account deleted");
    }

}
