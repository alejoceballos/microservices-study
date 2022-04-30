package ceballos.bank.loan.controller;

import ceballos.bank.loan.model.Loan;
import ceballos.bank.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/loans")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanController {

    private final LoanService service;

    @GetMapping
    public List<Loan> getBy(@RequestParam(required = false) Long customerId) {
        return service.findBy(customerId);
    }

}
