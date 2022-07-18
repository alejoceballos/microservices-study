package ceballos.bank.account.controller;

import ceballos.bank.account.model.Account;
import ceballos.bank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService service;

    @GetMapping
    public List<Account> getBy(@RequestParam(required = false) Long customerId) {
        final List<Account> accounts = service.findBy(customerId);
        return accounts;
    }

    @GetMapping(value = "/{id}")
    public Account getById(@PathVariable final Long id) {
        final Account account = service.findById(id);
        return account;
    }

}
