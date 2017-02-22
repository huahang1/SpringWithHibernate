package com.chris.service;

import com.chris.model.Card;

import java.util.List;

/**
 * Created by hanghua on 2/14/17.
 */
public interface ICardService {

    public void addCard(Card card, Integer person_id);

    public void updateCard(Card card,Integer person_id);

    public List<Card> getAllCards(Integer person_id);

    public List<Card> getAllCards();

    public Card getCardById(int card_id);

    public void deleteCard(int card_id);
}
