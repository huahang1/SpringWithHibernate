package com.chris.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hanghua on 2/13/17.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_country")
    private String country;

    @OneToMany(mappedBy = "person")
    private Set<Card> cards;

    public Person(){};

    public Person(String name, String country){
        this.name =name;
        this.country = country;
    }

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

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", cards=" + cards +
                '}';
    }

}
