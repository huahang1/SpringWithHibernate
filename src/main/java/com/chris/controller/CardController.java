package com.chris.controller;

import com.chris.model.Card;
import com.chris.service.ICardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by hanghua on 2/14/17.
 */
@Controller
@RequestMapping("/main/card")
public class CardController {

    @Resource(name = "cardService")
    private ICardService cardService;

    /**
     * Retrieves the "Add New Credit Card" page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(@RequestParam("id") Integer person_id, Model model) {

        // Prepare model object
        Card card= new Card();

        // Add to model
        model.addAttribute("person_id", person_id);
        model.addAttribute("cardAttribute", card);

        // This will resolve to /WEB-INF/jsp/add-credit-card.jsp
        return "add-card";
    }

    /**
     * Adds a new credit card
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@RequestParam("id") Integer person_id,
                          @ModelAttribute("cardAttribute") Card card) {

        System.out.println("person_id in CardController at add part is===== " + person_id);

        System.out.println("card number in CardController at add part is===== " + card.getNumber());

        cardService.addCard(card,person_id);

        return "redirect:/main/record/list";
    }


    /**
     * Deletes a credit card
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer card_id) {

        // Delegate to service
        cardService.deleteCard(card_id);

        // Redirect to url
        return "redirect:/main/record/list";
    }

    /**
     * Retrieves the "Edit Existing Credit Card" page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("pid") Integer person_id,
                          @RequestParam("cid") Integer card_id, Model model) {

        // Retrieve credit card by id
        Card existingCard = cardService.getCardById(card_id);

        // Add to model
        model.addAttribute("person_id", person_id);
        model.addAttribute("cardAttribute", existingCard);

        // This will resolve to /WEB-INF/jsp/edit-credit-card.jsp
        return "edit-card";
    }

    /**
     * Edits an existing credit card
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer card_id,
                           @RequestParam("pid") Integer person_id,
                           @ModelAttribute("creditCardAttribute") Card card) {

        // Assign id
        card.setId(card_id);

        // Delegate to service
        cardService.updateCard(card,person_id);

        // Redirect to url
        return "redirect:/main/record/list";
    }
}