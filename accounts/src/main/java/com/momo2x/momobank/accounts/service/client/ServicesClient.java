package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.AccountsCrossCuttingConcernInfo;
import com.momo2x.momobank.accounts.dto.CardDto;
import com.momo2x.momobank.accounts.dto.LoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class ServicesClient {

    private final AccountsCrossCuttingConcernInfo info;

    private final LoansClient loans;
    private final CardsClient cards;


    public LoanDto findLoanByMobileNumber(final String mobileNumber) {
        return ofNullable(loans.findByMobileNumber(info.getCorrelationId(), mobileNumber))
                .map(ResponseEntity::getBody)
                .orElse(null);
    }

    public CardDto findCardByMobileNumber(final String mobileNumber) {
        return ofNullable(cards.findByMobileNumber(info.getCorrelationId(), mobileNumber))
                .map(ResponseEntity::getBody)
                .orElse(null);
    }

}
