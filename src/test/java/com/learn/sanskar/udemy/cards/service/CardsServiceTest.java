package com.learn.sanskar.udemy.cards.service;

import com.learn.sanskar.udemy.cards.model.Card;
import com.learn.sanskar.udemy.cards.model.Customer;
import com.learn.sanskar.udemy.cards.repository.CardsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardsServiceTest {

    private static final String CARD_NUMBER = "1234";
    private static final Integer CARD_ID = 1;
    private static final Integer CUSTOMER_ID = 2;
    private static final String CARD_TYPE = "card_type";
    private static final Integer TOTAL_LIMIT = 1000;
    private static final Integer AMOUNT_USED = 600;
    private static final Integer AVAILABLE_AMOUNT = 400;
    private static final Date CREATE_DT = new Date();

    @Mock
    private CardsRepository cardsRepository;

    @InjectMocks
    private CardsService cardsService;

    @Test
    void givenCustomer_whenGetCardsByCustomer_thenReturnCards() {
        List<Card> expectedCards = List.of(createCard());
        Customer customer = new Customer(CUSTOMER_ID);
        when(cardsRepository.findByCustomerId(anyInt())).thenReturn(expectedCards);

        List<Card> actualCards = cardsService.getCardsByCustomer(customer);
        assertThat(actualCards).isNotNull();
        assertThat(actualCards).isNotEmpty();
        assertThat(actualCards.size()).isEqualTo(1);
    }

    @Test
    void givenCustomerNotExist_whenGetCardsByCustomer_thenReturnEmptyList() {
        Customer customer = new Customer(1222);
        when(cardsRepository.findByCustomerId(anyInt())).thenReturn(new ArrayList<>());

        List<Card> actualCards = cardsService.getCardsByCustomer(customer);
        assertThat(actualCards).isNotNull();
        assertThat(actualCards).isEmpty();
    }

    private Card createCard() {
        return new Card()
                .cardNumber(CARD_NUMBER)
                .cardNumber(CARD_NUMBER)
                .cardId(CARD_ID)
                .customerId(CUSTOMER_ID)
                .amountUsed(AMOUNT_USED)
                .availableAmount(AVAILABLE_AMOUNT)
                .totalLimit(TOTAL_LIMIT)
                .createDt(CREATE_DT)
                .cardType(CARD_TYPE);
    }
}