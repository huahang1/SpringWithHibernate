package com.chris.dao.impl;

import com.chris.dao.IPersonDao;
import com.chris.model.Person;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanghua on 2/13/17.
 */
@Repository("personDao")
public class PersonDaoImpl implements IPersonDao {

    //log specific info to logger for trace
    private static final Logger logger = Logger.getLogger(PersonDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void addPerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(person);
        logger.info("Person saved successfully, detail: " + person);

    }

    public void updatePerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(person);
        logger.info("Person updated successfully, detail: " + person);
    }

    // to avoid the check for query
    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from com.chris.model.Person");
        List results = query.list();
        for(Object person: results){
            Person person1 = (Person) person;
            System.out.println("person info in list persons=========== "+person1);
        }
        return results;
    }

    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class,id);
        logger.info("getPersonById successfully, person info: " + person);
        return person;
    }

    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.get(Person.class,id);
        session.delete(person);
        logger.info("Person delete successfully, person info: " + person);
    }
}
