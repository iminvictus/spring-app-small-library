package ru.ratnikov.lessons.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ratnikov.lessons.dao.PersonDAO;

/**
 * @author Mikhail Ratnikov
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping ()
    public String index(Model model) {
        // get all people from DAO and deliver them to the VIEW
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model) {
        //get one person from DAO by id and deliver one to the VIEW
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
}
