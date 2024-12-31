package com.momo2x.momobank.cards.service;

import com.momo2x.momobank.cards.dto.CardDto;
import com.momo2x.momobank.cards.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card) {
        return CardDto
                .builder()
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .mobileNumber(card.getMobileNumber())
                .totalLimit(card.getTotalLimit())
                .availableAmount(card.getAvailableAmount())
                .amountUsed(card.getAmountUsed())
                .build();
    }

}
