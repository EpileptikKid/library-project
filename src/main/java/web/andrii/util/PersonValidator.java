package web.andrii.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import web.andrii.dao.PersonDAO;


@Component
public class PersonValidator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }




}
