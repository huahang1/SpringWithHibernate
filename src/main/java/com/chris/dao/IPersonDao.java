package com.chris.dao;

import com.chris.model.Person;

import java.util.List;

/**
 * Created by hanghua on 2/13/17.
 */
public interface IPersonDao {

    public void addPerson(Person person);

    public void updatePerson(Person person);

    public List<Person> listPersons();

    public Person getPersonById(int id);

    public void removePerson(int id);
}
