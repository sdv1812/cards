package com.learn.sanskar.udemy.cards.web;

import com.learn.sanskar.udemy.cards.model.Card;
import com.learn.sanskar.udemy.cards.model.Customer;
import com.learn.sanskar.udemy.cards.service.CardsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    private final CardsService cardsService;

    @Autowired
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }
    @PostMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCardsByCustomer(@RequestBody @Valid Customer customer) {
        return cardsService.getCardsByCustomer(customer);
    }
}
