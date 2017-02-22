package com.chris.controller;

import com.chris.model.Person;
import com.chris.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hanghua on 2/13/17.
 */
@Controller
public class PersonController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        System.out.println("personController works");
        return "index";
    }

    @Autowired
    private IPersonService personService;

    @Qualifier(value = "personService")
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model){
        model.addAttribute("person",new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person){
        if(person.getId() == 0){
            //this is new person
            this.personService.addPerson(person);
        }else{
//            System.out.println("id from front-end: "+ id);
            //this is not new person, update it
            this.personService.updatePerson(person);
//            System.out.println("person id: "+ person.getId());
        }
        return "redirect:/persons";
    }

    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public String removePerson(@PathVariable("id") int id){
        this.personService.removePerson(id);

        return "redirect:/persons";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",this.personService.getPersonById(id));
        model.addAttribute("listPersons",this.personService.listPersons());
        return "person";
    }
}
