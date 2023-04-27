package com.learn.sanskar.udemy.cards.repository;

import com.learn.sanskar.udemy.cards.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsRepository extends JpaRepository<Card, Integer> {
    List<Card> findByCustomerId(int customerId);
}
