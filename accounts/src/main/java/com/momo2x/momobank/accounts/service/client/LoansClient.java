package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("loans")
public interface LoansClient {

    @GetMapping(value = "/api/v1/loans", consumes = {APPLICATION_JSON_VALUE})
    ResponseEntity<LoanDto> findByMobileNumber(@RequestParam String mobileNumber);

}