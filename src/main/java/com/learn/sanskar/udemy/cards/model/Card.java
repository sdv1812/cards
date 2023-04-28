package com.learn.sanskar.udemy.cards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter @Setter @ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Integer cardId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "total_limit")
    private Integer totalLimit;
    @Column(name = "amount_used")
    private Integer amountUsed;
    @Column(name = "available_amount")
    private Integer availableAmount;
    @Column(name = "create_dt")
    private Date createDt;

    public Card cardId(Integer cardId) {
        this.cardId = cardId;
        return this;
    }

    public Card customerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public Card cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public Card cardType(String cardType) {
        this.cardType = cardType;
        return this;
    }

    public Card totalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
        return this;
    }

    public Card amountUsed(Integer amountUsed) {
        this.amountUsed = amountUsed;
        return this;
    }

    public Card availableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
        return this;
    }

    public Card createDt(Date createDt) {
        this.createDt = createDt;
        return this;
    }
}
