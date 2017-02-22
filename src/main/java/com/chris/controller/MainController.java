package com.chris.controller;

import com.chris.DTO.PersonDTO;
import com.chris.model.Person;
import com.chris.service.ICardService;
import com.chris.service.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanghua on 2/14/17.
 */
@Controller
@RequestMapping("/main/record")
public class MainController {

    @Resource(name = "personService")
    private IPersonService personService;

    @Resource(name = "cardService")
    private ICardService cardService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String getRecords(Model model){

        List<Person> persons = personService.listPersons();

        List<PersonDTO> personDTO = new ArrayList<PersonDTO>();

        for(Person person: persons){
            PersonDTO dto = new PersonDTO();
            dto.setId(person.getId());
            dto.setName(person.getName());
            dto.setCountry(person.getCountry());
            dto.setCards(cardService.getAllCards(person.getId()));
            personDTO.add(dto);
        }
        model.addAttribute("persons",personDTO);
        return "records";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        model.addAttribute("personAttribute", new Person());

        // This will resolve to /WEB-INF/jsp/add-records.jsp
        return "add-record";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("personAttribute") Person person) {

        // Delegate to service
        personService.addPerson(person);

        // Redirect to url
        return "redirect:/main/record/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer person_id) {

        // Delete person
        personService.removePerson(person_id);

        // Redirect to url
        return "redirect:/main/record/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer person_id, Model model) {

        // Retrieve person by id
        Person existingPerson = personService.getPersonById(person_id);

        // Add to model
        model.addAttribute("personAttribute", existingPerson);

        // This will resolve to /WEB-INF/jsp/edit-records.jsp
        return "edit-record";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer person_id,
                           @ModelAttribute("personAttribute") Person person) {

        // Assign id
        person.setId(person_id);

        // Delegate to service
        personService.updatePerson(person);

        // Redirect to url
        return "redirect:/main/record/list";
    }
}
