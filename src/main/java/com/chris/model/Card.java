package com.chris.model;

import javax.persistence.*;

/**
 * Created by hanghua on 2/13/17.
 */
@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id",nullable = false)
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Card(){};

    public Card(Integer id,String number,Person person){
        this.id=id;
        this.number=number;
        this.person = person;
    }

}
