package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallbackLoansClient implements LoansClient {

    @Override
    public ResponseEntity<LoanDto> findByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }

}
