package ceballos.bank.account.service;

import ceballos.bank.account.model.Account;
import ceballos.bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final AccountRepository repository;

    public List<Account> findBy(final Long customerId) {
        List<Account> accounts = repository.findAllByCustomerId(customerId);
        return accounts;
    }


    public Account findById(final Long id) {
        Account account = repository.findById(id).orElse(null);
        return account;
    }
}
