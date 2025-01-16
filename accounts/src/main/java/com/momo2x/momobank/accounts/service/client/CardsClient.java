package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("cards")
public interface CardsClient {

    @GetMapping(value = "/api/v1/cards", consumes = {APPLICATION_JSON_VALUE})
    ResponseEntity<CardDto> findByMobileNumber(@RequestParam String mobileNumber);

}
