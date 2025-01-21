package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallbackCardsClient implements CardsClient {

    @Override
    public ResponseEntity<CardDto> findByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
