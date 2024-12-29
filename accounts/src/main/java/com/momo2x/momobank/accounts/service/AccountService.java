package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.AccountDto;
import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.entity.Account;
import com.momo2x.momobank.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.momo2x.momobank.accounts.exception.ResourceNotFoundException.notFoundExceptionSupplier;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AccountService {

    private static final Random RANDOM = new Random();

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    @Transactional
    public AccountDto create(final CustomerDto customerDto) {
        return accountMapper.toDto(
                accountRepository.save(
                        Account.builder()
                                .accountNumber(newAccountNumber())
                                .customerId(customerService.create(customerDto).getCustomerId())
                                .accountType("SAVINGS")
                                .branchAddress("BRANCH_ADDRESS")
                                .build()));
    }

    public AccountDto findByCustomerMobileNumber(final String mobileNumber) {
        final var accountDto = accountMapper.toDto(
                accountRepository
                        .findById(customerService.findCustomerIdByMobileNumber(mobileNumber))
                        .orElseThrow(notFoundExceptionSupplier(
                                Account.class,
                                format("customer's mobile number %s", mobileNumber))));

        accountDto.setCustomer(customerService.findByMobileNumber(mobileNumber));

        return accountDto;
    }

    @Transactional
    public AccountDto update(final AccountDto accountDto) {
        final var loadedAccount = accountRepository
                .findById(accountDto.getAccountNumber())
                .orElseThrow(notFoundExceptionSupplier(
                        Account.class,
                        accountDto.getAccountNumber()));

        loadedAccount.setAccountType(accountDto.getAccountType());
        loadedAccount.setBranchAddress(accountDto.getBranchAddress());

        final var savedAccountDto = accountMapper.toDto(
                accountRepository.save(loadedAccount));

        savedAccountDto.setCustomer(
                customerService.updateByMobileNumber(accountDto.getCustomer()));

        return savedAccountDto;
    }

    private static Long newAccountNumber() {
        return 1_000_000_000L + RANDOM.nextInt(900_000_000);
    }

    @Transactional
    public void deleteByAccountNumber(final Long accountNumber) {
        final var accountDto =
                accountRepository
                        .findById(accountNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Account.class,
                                accountNumber));

        accountRepository.deleteById(accountDto.getAccountNumber());
        customerService.deleteById(accountDto.getCustomerId());
    }

}
