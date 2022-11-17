package ru.ratnikov.lessons.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ratnikov.lessons.dao.PersonDAO;
import ru.ratnikov.lessons.models.Person;

/**
 * @author Mikhail Ratnikov
 */
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDAO.findPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Person with the name is already in database");
        }
    }
}
