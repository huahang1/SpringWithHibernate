package com.chris.service.impl;

import com.chris.dao.ICardDao;
import com.chris.model.Card;
import com.chris.service.ICardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanghua on 2/14/17.
 */
@Service("cardService")
public class CardServiceImpl implements ICardService {

    @Resource(name = "cardDao")
    private ICardDao cardDao;

    public void setCardDao(ICardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Transactional
    public void addCard(Card card,Integer person_id) {
        this.cardDao.addCard(card,person_id);
    }

    @Transactional
    public void updateCard(Card card,Integer person_id) {
        this.cardDao.updateCard(card,person_id);
    }

    @Transactional
    public List<Card> getAllCards() {
        return this.cardDao.getAllCards();
    }

    @Transactional
    public List<Card> getAllCards(Integer person_id){
        return  this.cardDao.getAllCards(person_id);
    }

    @Transactional
    public Card getCardById(int card_id) {
        return this.cardDao.getCardById(card_id);
    }

    @Transactional
    public void deleteCard(int card_id) {
        this.cardDao.deleteCard(card_id);
    }
}
