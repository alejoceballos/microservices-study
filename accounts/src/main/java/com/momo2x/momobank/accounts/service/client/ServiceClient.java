package com.momo2x.momobank.accounts.service.client;

import com.momo2x.momobank.accounts.dto.AccountsCrossCuttingConcernInfo;
import com.momo2x.momobank.accounts.dto.CardDto;
import com.momo2x.momobank.accounts.dto.LoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceClient {

    private final AccountsCrossCuttingConcernInfo info;

    private final LoansClient loans;
    private final CardsClient cards;


    public LoanDto findLoanByMobileNumber(final String mobileNumber) {
        return loans.findByMobileNumber(info.getCorrelationId(), mobileNumber).getBody();
    }

    public CardDto findCardByMobileNumber(final String mobileNumber) {
        return cards.findByMobileNumber(info.getCorrelationId(), mobileNumber).getBody();
    }

}
