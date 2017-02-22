package com.chris.dao.impl;

import com.chris.dao.ICardDao;
import com.chris.model.Card;
import com.chris.model.Person;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hanghua on 2/14/17.
 */
@Repository("cardDao")
public class CardDaoImpl implements ICardDao {

    private static final Logger logger = Logger.getLogger(CardDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    public void addCard(Card card, Integer person_id) {
        Session session = this.sessionFactory.getCurrentSession();

        //get the person object through person_id
        Person person = (Person) session.get(Person.class,person_id);

        Card card1 = new Card(card.getId(),card.getNumber(),person);

        System.out.println("person info in addCard part=========== " + person);

        Set<Card> cardSet = new HashSet<Card>();

        cardSet.add(card1);

        person.setCards(cardSet);

//        person.getCards().add(card);

        System.out.println("after adding card of peronInfo ============ " + person);

        session.save(person);

        session.save(card1);

        System.out.println("card1 info ==============" + card1);

        System.out.println("person object in cardDao part is null or not: ====== "+ person.equals(null));

        logger.info("Card saved successfully, detail: " + card1);
    }

    public void updateCard(Card card,Integer person_id) {
        Session session = this.sessionFactory.getCurrentSession();

        Person person = (Person) session.get(Person.class,person_id);

        Card card1 = new Card(card.getId(),card.getNumber(),person);

        session.update(card1);

        logger.info("Card updated successfully, detail: " + card1);
    }

    @SuppressWarnings("unchecked")
    public List<Card> getAllCards(Integer person_id) {

        Session session = this.sessionFactory.getCurrentSession();

        Person person = (Person) session.get(Person.class,person_id);

        return new ArrayList<Card>(person.getCards());
    }

    @SuppressWarnings("unchecked")
    public List<Card> getAllCards() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM com.chris.model.Card");
        return query.list();
    }


    public Card getCardById(int card_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Card card = (Card) session.load(Card.class,card_id);
        logger.info("getCardById successfully, card info: " + card);
        return card;
    }

    public void deleteCard(int card_id) {
        Session session = this.sessionFactory.getCurrentSession();
        //we should have a joined table made by table person and table card
        Card card = (Card) session.load(Card.class,card_id);

        if(card != null){
            session.delete(card);
        }

        logger.info("Card delete successfully, deleted info: " + card);

    }
}
