package com.momo2x.momobank.loans.service;

import com.momo2x.momobank.loans.dto.LoanDto;
import com.momo2x.momobank.loans.entity.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanDto toDto(Loan loan) {
        return LoanDto
                .builder()
                .loanNumber(loan.getLoanNumber())
                .loanType(loan.getLoanType())
                .mobileNumber(loan.getMobileNumber())
                .totalLoan(loan.getTotalLoan())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .build();
    }

}
