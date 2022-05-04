package ceballos.bank.card.controller;

import ceballos.bank.card.model.Card;
import ceballos.bank.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cards")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {

    private final CardService service;

    @GetMapping
    public List<Card> getBy(@RequestParam(required = false) Long customerId) {
        return service.findBy(customerId);
    }

}
