package com.chris.service.impl;

import com.chris.dao.IPersonDao;
import com.chris.model.Person;
import com.chris.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanghua on 2/13/17.
 */
@Service("personService")
public class PersonServiceImpl implements IPersonService {

    @Resource(name = "personDao")
    private IPersonDao personDao;

    public void setPersonDao(IPersonDao personDao) {
        this.personDao = personDao;
    }

    //add transactional annotation to mark this part has transaction manager feature so it will commit and rollback if any exception
    @Transactional
    public void addPerson(Person person) {
        this.personDao.addPerson(person);

    }

    //transaction manager manifests based on the AOP, by around method. Only when the return result is successfully, the method is gonna be executed
    @Transactional
    public void updatePerson(Person person) {
        this.personDao.updatePerson(person);
    }

    @Transactional
    public List<Person> listPersons() {
        return this.personDao.listPersons();
    }

    @Transactional
    public Person getPersonById(int id) {
        return this.personDao.getPersonById(id);
    }

    @Transactional
    public void removePerson(int id) {
        this.personDao.removePerson(id);
    }
}
