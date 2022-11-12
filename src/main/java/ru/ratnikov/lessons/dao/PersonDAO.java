package ru.ratnikov.lessons.dao;

import org.springframework.stereotype.Component;
import ru.ratnikov.lessons.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Ratnikov
 */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Jeremy"));
        people.add(new Person(++PEOPLE_COUNT, "Scott"));
        people.add(new Person(++PEOPLE_COUNT, "Chester"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save (Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
