package ceballos.bank.loan.service;

import ceballos.bank.loan.model.Loan;
import ceballos.bank.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoanService {

    private final LoanRepository repository;

    public List<Loan> findBy(@RequestParam Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }


}
