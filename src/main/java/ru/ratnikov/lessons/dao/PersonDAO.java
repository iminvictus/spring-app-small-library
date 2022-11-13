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

        people.add(new Person(++PEOPLE_COUNT, "Mike", 20, "mike@emails.com"));
        people.add(new Person(++PEOPLE_COUNT, "Jeremy", 25, "jeremy@emails.com"));
        people.add(new Person(++PEOPLE_COUNT, "Scott", 30, "scott@emails.com"));
        people.add(new Person(++PEOPLE_COUNT, "Chester", 35, "chester@emails.com"));
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

    public void update (int id, Person person) {
        Person personToUpdate = show(id);

        personToUpdate.setName(person.getName());
        personToUpdate.setAge(person.getAge());
        personToUpdate.setEmail(person.getEmail());
    }

    public void delete (int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
