package web.andrii.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.andrii.dao.PersonDAO;
import web.andrii.models.Person;

@Controller
@RequestMapping("/library")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/people")
    public String index(Model model) {
        model.addAttribute("people", personDAO.selectAllPerson());
        return "library/people/index";
    }

    @GetMapping("/people/{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "library/people/person";
    }

    @GetMapping("/new-person")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "library/people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/library/people/index";
    }

    @PostMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "library/people/edit";
    }

    @PostMapping("/people/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/library/people";
    }

    @PostMapping("/people/{id}")
    public String setNewDataPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.update(id, person);
        return "redirect:/library/people";
    }
}
