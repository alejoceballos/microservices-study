package com.momo2x.momobank.loans.service;

import com.momo2x.momobank.loans.dto.LoanDto;
import com.momo2x.momobank.loans.entity.Loan;
import com.momo2x.momobank.loans.exception.ResourceAlreadyExistsException;
import com.momo2x.momobank.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.momo2x.momobank.loans.exception.ResourceNotFoundException.notFoundExceptionSupplier;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class LoanService {

    private static final Random RANDOM = new Random();

    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    public LoanDto create(final String mobileNumber) {
        if (loanRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    Loan.class.getSimpleName(),
                    "mobileNumber",
                    mobileNumber);
        }

        return loanMapper.toDto(
                loanRepository.save(
                        Loan.builder()
                                .loanNumber(valueOf(RANDOM.nextInt()))
                                .mobileNumber(mobileNumber)
                                .loanType("HOME_LOAN")
                                .totalLoan(50_000)
                                .amountPaid(0)
                                .outstandingAmount(50_000)
                                .build()));
    }

    public LoanDto update(final LoanDto loanDto) {
        final var loadedLoan =
                loanRepository
                        .findByLoanNumber(loanDto.getLoanNumber())
                        .orElseThrow(notFoundExceptionSupplier(
                                Loan.class,
                                valueOf(loanDto.getMobileNumber())));

        loadedLoan.setLoanType(loanDto.getLoanType());
        loadedLoan.setTotalLoan(loanDto.getTotalLoan());
        loadedLoan.setAmountPaid(loanDto.getAmountPaid());
        loadedLoan.setOutstandingAmount(loanDto.getOutstandingAmount());

        return loanMapper.toDto(
                loanRepository.save(loadedLoan));
    }

    public LoanDto findByMobileNumber(final String mobileNumber) {
        return loanMapper.toDto(
                loanRepository
                        .findByMobileNumber(mobileNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Loan.class,
                                mobileNumber)));
    }

    public void deleteByLoanNumber(final String loanNumber) {
        loanRepository.deleteById(
                loanRepository
                        .findByLoanNumber(loanNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Loan.class,
                                valueOf(loanNumber)))
                        .getLoanId());
    }

}
