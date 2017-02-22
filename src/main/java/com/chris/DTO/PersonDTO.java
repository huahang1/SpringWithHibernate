package com.chris.DTO;

import com.chris.model.Card;

import java.util.List;

/**
 * Created by hanghua on 2/15/17.
 */
public class PersonDTO {

    private int id;

    private String name;

    private String country;

    private List<Card> Cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Card> getCards() {
        return Cards;
    }

    public void setCards(List<Card> cards) {
        Cards = cards;
    }
}
