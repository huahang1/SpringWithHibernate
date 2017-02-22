package com.chris.dao.impl;

import com.chris.model.Person;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;


import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hanghua on 2/21/17.
 */

//add @WebAppConfiguration to notate this is a web application so everything in this project should run in web application environment
@WebAppConfiguration
@ContextConfiguration("/dispatcher-servlet.xml")
//@ContextConfiguration(locations =  "classpath:dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//Usually
@Transactional(rollbackFor = Exception.class)
public class PersonDaoImplTest extends TestRules {

    private static final Logger logger = Logger.getLogger(PersonDaoImplTest.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Before
    public void CreateSession(){
//        session = sessionFactory.openSession();
        session = this.sessionFactory.getCurrentSession();
    }

    @Test
    @SuppressWarnings("checked")
    public void addPerson() throws Exception {
        //count
        Query query = session.createQuery("select count(*) from Person");
        Long countBefore = (Long) query.uniqueResult();
//        System.out.println("count before : " + countBefore);
        Person person = new Person("chris1","china");
        Assert.assertEquals("get the person's name ","chris1",person.getName());
        Assert.assertEquals("get the person's country ","china",person.getCountry());
        session.save(person);

        //count
        Long countAfter = (Long) query.uniqueResult();
//        System.out.println("count after : " + countAfter);
        Assert.assertNotEquals("the countAfter should not be as equal as countBefore ", countBefore,countAfter);


    }

    @Test
    public void updatePerson() throws Exception {
        Person person = (Person) session.get(Person.class,19);
        person.setName("chris19");
        person.setCountry("us");
        Person person1 = (Person) session.get(Person.class,19);
        Assert.assertNotNull(person1);
        Assert.assertEquals("get the person's name: ","chris19",person1.getName());
        Assert.assertEquals("get the person's country: ","us",person1.getCountry());

        //test for updating not-existing person
        Person person2 = (Person) session.get(Person.class,55);
        Assert.assertNull("updating not-existing person: ",person2);
    }

    @Test
    public void listPersons() throws Exception {
//        Query query = session.createQuery("from com.chris.model.Person");
        Query query = session.createQuery("from Person");
        List<Person> persons = query.list();
        Assert.assertNotNull("get all the persons info: ",persons);
//        assertThat(persons,hasItems(
//                new Person("chris1","china")
//        ));
        assertThat("check person's property: ",persons,contains(
                hasProperty("name",is("chris1")),
                hasProperty("country",is("china")),
                hasProperty("cards",is(new HashSet()))
        ));
    }

    @Test
    public void getPersonById() throws Exception {
        Person person = (Person) session.get(Person.class,19);
        Assert.assertNotNull(person);
        Assert.assertEquals("get person name: ","chris1",person.getName());
        Assert.assertEquals("get person country: ","china",person.getCountry());

        //test for non-existing id
        Person person1 = (Person) session.get(Person.class,55);
        Assert.assertNull("get the non-existing id: ",person1);
    }


    @Test
    public void removePerson() throws Exception {
        Person person = (Person) session.get(Person.class,23);
        session.delete(person);
        Person person1 = (Person) session.get(Person.class,23);
        Assert.assertNull("delete this person: ",person1);
    }

}