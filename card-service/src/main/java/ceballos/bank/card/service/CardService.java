package ceballos.bank.card.service;

import ceballos.bank.card.model.Card;
import ceballos.bank.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardService {

    private final CardRepository repository;

    public List<Card> findBy(@RequestParam Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }


}
