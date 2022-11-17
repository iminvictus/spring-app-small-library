package ru.ratnikov.lessons.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ratnikov.lessons.models.Book;
import ru.ratnikov.lessons.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mikhail Ratnikov
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> findPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save (Person person) {
        jdbcTemplate.update("INSERT INTO person(name, birth) VALUES(?, ?)",
                person.getName(), person.getBirth());
    }

    public void update (int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name = ?, birth = ? WHERE id = ?",
                person.getName(), person.getBirth(), id);
    }

    public void delete (int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public List<Book> getBooksOwnedByPersonId (int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
