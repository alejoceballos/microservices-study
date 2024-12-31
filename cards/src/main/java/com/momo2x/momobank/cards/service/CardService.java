package com.momo2x.momobank.cards.service;

import com.momo2x.momobank.cards.dto.CardDto;
import com.momo2x.momobank.cards.entity.Card;
import com.momo2x.momobank.cards.exception.ResourceAlreadyExistsException;
import com.momo2x.momobank.cards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.momo2x.momobank.cards.exception.ResourceNotFoundException.notFoundExceptionSupplier;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class CardService {

    private static final Random RANDOM = new Random();

    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public CardDto create(final String mobileNumber) {
        if (cardRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    Card.class.getSimpleName(),
                    "mobileNumber",
                    mobileNumber);
        }

        return cardMapper.toDto(
                cardRepository.save(
                        Card.builder()
                                .cardNumber(valueOf(RANDOM.nextInt()))
                                .mobileNumber(mobileNumber)
                                .cardType("CREDIT")
                                .totalLimit(50_000)
                                .amountUsed(0)
                                .availableAmount(50_000)
                                .build()));
    }

    public CardDto update(final CardDto cardDto) {
        final var loadedCard =
                cardRepository
                        .findByCardNumber(cardDto.getCardNumber())
                        .orElseThrow(notFoundExceptionSupplier(
                                Card.class,
                                valueOf(cardDto.getMobileNumber())));

        loadedCard.setCardType(cardDto.getCardType());
        loadedCard.setTotalLimit(cardDto.getTotalLimit());
        loadedCard.setAmountUsed(cardDto.getAmountUsed());
        loadedCard.setAvailableAmount(cardDto.getAvailableAmount());

        return cardMapper.toDto(
                cardRepository.save(loadedCard));
    }

    public CardDto findByMobileNumber(final String mobileNumber) {
        return cardMapper.toDto(
                cardRepository
                        .findByMobileNumber(mobileNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Card.class,
                                mobileNumber)));
    }

    public void deleteByCardNumber(final String cardNumber) {
        cardRepository.deleteById(
                cardRepository
                        .findByCardNumber(cardNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Card.class,
                                valueOf(cardNumber)))
                        .getCardId());
    }

}
