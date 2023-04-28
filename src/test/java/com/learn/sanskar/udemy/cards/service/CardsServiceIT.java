package com.learn.sanskar.udemy.cards.service;

import com.learn.sanskar.udemy.cards.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CardsServiceIT {

    private static final String CARD_NUMBER = "1234";
    private static final Integer CARD_ID = 1;
    private static final Integer CUSTOMER_ID = 2;
    private static final String CARD_TYPE = "card_type";
    private static final Integer TOTAL_LIMIT = 1000;
    private static final Integer AMOUNT_USED = 600;
    private static final Integer AVAILABLE_AMOUNT = 400;
    private static final Date CREATE_DT = new Date();

    @Autowired
    private CardsService cardsService;

    @Test
    void givenCard_whenSave_thenSaveAndReturnSavedCard() {
        Card card = new Card()
                .cardNumber(CARD_NUMBER)
                .customerId(CUSTOMER_ID)
                .amountUsed(AMOUNT_USED)
                .availableAmount(AVAILABLE_AMOUNT)
                .totalLimit(TOTAL_LIMIT)
                .createDt(CREATE_DT)
                .cardType(CARD_TYPE);

        System.out.printf("input card before saving= %s \n", card);

        Card savedCard = cardsService.saveCard(card);
        assertThat(savedCard).isNotNull();
        assertThat(savedCard.getCardId()).isNotNull();
        System.out.printf("input card = %s \n", card);
        System.out.printf("saved card = %s \n", savedCard);
    }
}
