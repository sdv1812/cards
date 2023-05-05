package com.learn.sanskar.udemy.cards.web;

import com.learn.sanskar.udemy.cards.model.Card;
import com.learn.sanskar.udemy.cards.model.Customer;
import com.learn.sanskar.udemy.cards.service.CardsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardsController.class)
class   CardsControllerTest {

    private static final String CARD_NUMBER = "1234";
    private static final Integer CARD_ID = 1;
    private static final Integer CUSTOMER_ID = 2;
    private static final String CARD_TYPE = "card_type";
    private static final Integer TOTAL_LIMIT = 1000;
    private static final Integer AMOUNT_USED = 600;
    private static final Integer AVAILABLE_AMOUNT = 400;
    private static final Date CREATE_DT = new Date();

    @MockBean
    private CardsService cardsService;

    @Autowired
    private CardsController cardsController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenCustomer_whenGetCardsByCustomer_thenReturnCards() throws Exception {
        List<Card> expectedCards = List.of(createCard());
        when(cardsService.getCardsByCustomer(any(Customer.class))).thenReturn(expectedCards);

        String GET_CARDS_URL = "/card";
        this.mockMvc.perform(post(GET_CARDS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "customerId": 2
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isNotEmpty())
                .andExpect(jsonPath("$[0].cardNumber").value(CARD_NUMBER))
                .andExpect(jsonPath("$[0].cardId").value(CARD_ID))
                .andExpect(jsonPath("$[0].customerId").value(CUSTOMER_ID))
                .andExpect(jsonPath("$[*].cardNumber").value(containsInAnyOrder(CARD_NUMBER)))
                .andExpect(jsonPath("$[*].customerId").value(containsInAnyOrder(CUSTOMER_ID)));

    }

    private Card createCard() {
        return new Card()
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