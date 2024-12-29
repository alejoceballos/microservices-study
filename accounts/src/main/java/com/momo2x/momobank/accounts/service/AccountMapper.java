package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.AccountDto;
import com.momo2x.momobank.accounts.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDto toDto(Account account) {
        return AccountDto
                .builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }

}
