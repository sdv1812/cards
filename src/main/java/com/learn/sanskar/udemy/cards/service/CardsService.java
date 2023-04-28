package com.learn.sanskar.udemy.cards.service;

import com.learn.sanskar.udemy.cards.model.Card;
import com.learn.sanskar.udemy.cards.model.Customer;
import com.learn.sanskar.udemy.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public List<Card> getCardsByCustomer(Customer customer) {
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }

    // just for learning and testing using transactional
    public Card saveCard(Card card) {
        return cardsRepository.save(card);
    }
}
